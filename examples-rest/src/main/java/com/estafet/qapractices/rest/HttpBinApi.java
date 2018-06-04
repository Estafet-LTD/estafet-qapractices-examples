package com.estafet.qapractices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Pesho on 15-Sep-17.
 */

/**
 * This file describes the resources we are going to use.
 * The name tells us what service it's about.
 * Feel free to add more resources from HttpBin.
 * The new resources should be added in the following format:
 *
 * @GET - it describes the (ReST) method this (Java) method will accept-
 * GET, POST, PUT, DELETE. Only one parameter is allowed!
 * @Path("/get") - it describes the relative path for the resource. It's full path is "http://httpbin.org" + "/get".
 * The main URL is loaded in HttpBitTasks from the yml (configuration) file. The resource allows for the use of
 * parameters (described in the methods as @PathParam for when we don't want a hardcoded resources.
 * For more information- Google it!
 * @Consumes("application/x-www-form-urlencoded") - this is basically the Accept header.
 * Response getMethod() - this is the hairy part. Please refer to:
 * https://examples.javacodegeeks.com/enterprise-java/rest/resteasy/resteasy-hello-world-example/
 * for basic examples.
 */
public interface HttpBinApi {

    @GET
    @Path("/get")
    @Consumes("application/x-www-form-urlencoded")
    Response getMethod();

    @POST
    @Path("/post")
    @Consumes("application/x-www-form-urlencoded")
    Response postMethod(@QueryParam("userName") String username, @QueryParam("length") String length,
                        @QueryParam("cupSize") String cupSize, @QueryParam("measures") String measures);
}
 

