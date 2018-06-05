package com.estafet.qapractices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Petar Petrov on 07-Nov-17.
 */
public interface ReqResApi {

    @GET
    @Path("/users")
    @Consumes("application/x-www-form-urlencoded")
    Response listUsers(@QueryParam("per_page") int per_page);

    @POST
    @Path("/users")
    @Consumes("application/x-www-form-urlencoded")
    Response createUser(String body);

    @PUT
    @Path("/users")
    @Consumes("application/x-www-form-urlencoded")
    Response updateUser(@PathParam("") String index,
                        String body);
    @DELETE
    @Path("/users/")
    @Consumes("application/x-www-form-urlencoded")
    Response deleteUser(@PathParam("id") String id);
}
