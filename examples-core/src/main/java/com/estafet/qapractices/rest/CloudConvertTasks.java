package com.estafet.qapractices.rest;

import com.estafet.qapractices.env.Environment;
import com.google.inject.Inject;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

/**
 * Created by Petar Petrov on 07-Nov-17.
 */
public class CloudConvertTasks {

    private Environment env;
    private CloudConvertApi cloudConvertApi;

    @Inject
    public CloudConvertTasks(final Environment env) {
        this.env = env;
        this.cloudConvertApi = getTarget().proxy(CloudConvertApi.class);
    }

    public Response createProcess(final String input, final String output) {
        return cloudConvertApi.createProcess(input, output);
    }

    private ResteasyWebTarget getTarget() {
        final ResteasyClient client = new ResteasyClientBuilder().build();
        final String apiKey = env.getProperty("cloudConvertKey");
        final Cookie cookie = new Cookie("Bearer", apiKey);
        client.register(new CookieClientRequestFilter(cookie));
        return client.target(env.getProperty("cloudConvertUrl"));
    }
}
