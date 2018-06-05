package com.estafet.qapractices.core;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.model.ReqResUserModel;
import com.estafet.qapractices.model.ReqResUsersModel;
import com.google.gson.Gson;
import com.google.inject.Inject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

/* Created by Anton Rusanov on 28/05/18
 *  This class contains methods for basic requests with Rest Assured.
 */
public class ReqRest {

	private Context context;
	
	private Response response;
	private ValidatableResponse validateResponse;

	@Inject
	public ReqRest(Context context) {
		this.context = context; 

	}

	public void getReqWithVerify() {
		/*
		 * With ValidateResponse class, we can make request(GET,PUT,POST ... etc.) and verifications and assertions without using the Assert or SoftAssert.
		 */
		validateResponse = RestAssured.given().contentType("application/json").when().get().then()
				.statusCode(200);

	}

	public Response getRequestRestApi()  {
		int pollingIntervalSeconds = 5;
		int pollingRangeTimeout = 60;
		try {
			/*
	          This is Awaitility. This block polls the API endpoint until the response code is 200.
	          The lambda expression is executed every "pollingIntervalInSeconds" for max period of "pollTimeoutInSeconds".
	          If the expression returns True, the execution continues.
	          If it does not before "pollTimeoutInSeconds" is reached, a ConditionTimeoutException is thrown.
	         */

			Awaitility.await().pollInterval(pollingIntervalSeconds, TimeUnit.SECONDS)
					.atMost(pollingRangeTimeout, TimeUnit.SECONDS).await().until(() -> {
						response = RestAssured.given().when().get();
						context.saveData("get response", response.getStatusCode());
						context.saveData("body", response.asString());
						return response.getStatusCode() == 200;
					});
		} catch (ConditionTimeoutException e) {
			try {
				throw new Exception(e.getMessage() + " status code is not 200");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return response;

	}

	public void postRequestRestApi(String name) {
		String firstName = name.split(" ")[0];
		String lastName = name.split(" ")[1];
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("first_name", firstName);
		mapParam.put("last_name", lastName);
		/*
		 * The basic structure of the request goes first with given() -> here it loads the URI , after this is the contentType -> format of the message,
		 *  body() -> here we post the required data, when() -> requested specification and the request(in this case is post) ->  with the requested path.
		 */
		response = RestAssured.given().contentType("application/json").body(mapParam).when().post();

		assertTrue("Status code is not 201",response.getStatusCode() == 201);
		context.saveData("post response", response.getStatusCode());
		context.saveData("body", response.asString());

	}

	public void getResponseCode() {

		int status = (int) context.getSavedData("get response");
		String body = (String) context.getSavedData("body");
		System.out.println(status);
		System.out.println(body);
		SoftAssertions softAssert = new SoftAssertions();
		softAssert.assertThat(status == 200);
		softAssert.assertThat(body.contains("page"));
		softAssert.assertAll();

	}

	public void postResponseCode() {
		int status = (int) context.getSavedData("post response");
		String body = (String) context.getSavedData("body");
		System.out.println(status);
		System.out.println(body);
		SoftAssertions softAssert = new SoftAssertions();
		softAssert.assertThat(status == 201);
		softAssert.assertAll();
	}

	public ReqResUsersModel listAllUsers() {
		final Gson gson = new Gson();
		String users = RestAssured.given().queryParam("per_page", 1000).get().getBody().asString();

		ReqResUsersModel model = gson.fromJson(users, ReqResUsersModel.class);

		return model;

	}

	public void chekUserAndGetId(String name) {
		Map<String, String> userMap = new HashMap<>();
		String firstName = name.split(" ")[0];
		String lastName = name.split(" ")[1];

		userMap.put("first_name", firstName);
		userMap.put("last_name", lastName);
		List<ReqResUserModel> user = listAllUsers().getData();
		for (ReqResUserModel curUser : user) {
			if (curUser.getFirstName().equals(firstName) && curUser.getLastName().equals(lastName)) {
				context.saveData("userId", curUser.getId());

			}
		}

	}

	public void putRequestRestApiById(String name) {
		Map<String, String> userMap = new HashMap<>();
		String firstName = name.split(" ")[0];
		String lastName = name.split(" ")[1];
		String userId = (String) context.getSavedData("userId");
		userMap.put("first_name", firstName);
		userMap.put("last_name", lastName);

		response = RestAssured.given().contentType("application/json").body(userMap).when()
				.put(userId);
		System.out.println(response.getBody().asString());
		SoftAssertions soft = new SoftAssertions();
		soft.assertThat(response.getStatusCode() == 200);
		soft.assertAll();
	}

	public void deleteUserById() {
		String userId = (String) context.getSavedData("userId");
		response = RestAssured.given().contentType("application/json").when()
				.delete(userId);
		System.out.println(response.getStatusCode());
		assertTrue("Status code is not 204",response.getStatusCode() == 204);

	}

}
