package com.estafet.qapractices.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by Petar Petrov on 07-Nov-17.
 */
public interface CloudConvertApi {

    @POST
    @Path("/process")
    @Consumes("application/x-www-form-urlencoded")
    Response createProcess(@QueryParam("inputformat") String inputFormat,
                           @QueryParam("outputformat") String outputFormat);
}
