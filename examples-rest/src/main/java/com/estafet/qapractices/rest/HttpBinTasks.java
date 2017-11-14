package com.estafet.qapractices.rest;

import com.estafet.qapractices.env.Environment;
import com.estafet.qapractices.models.HttpBinModel;
import com.google.inject.Inject;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

/**
 * Created by Pesho on 15-Sep-17.
 */

/**
 * This method configures the ReSTEasy client for a target- in this case HttpBin.
 * Do not instantiate this class- inject it.
 * Use this class for reference when creating new ReSTEasy client for new services.
 */

//TODO It will be nice to add a custom response class in the future
public class HttpBinTasks {

    private Environment env;
    private HttpBinApi httpBinApi;

    /**
     * Constructor. It injects the environment file and assigns the env property to the one in this class- this.env.
     * getTarget().proxy(HttpBinApi.class) - loads the HttpBinApi as a target for the client
     */
    @Inject
    public HttpBinTasks(final Environment env) {
        this.env = env;
        this.httpBinApi = getTarget().proxy(HttpBinApi.class);
    }

    /**
     * getMethodResponse and postMethodResponse execute the call the corresponding methods from HttpBinApi.
     * This classes should be public and with meaningful names (not like these).
     * They are the ones being used in the step definitions.
     * Everything else should be private.
     */
    public Response getMethodResponse() {
        return httpBinApi.getMethod();
    }

    public Response postMethodResponse(final HttpBinModel model) {
        return httpBinApi.postMethod(model.getUserName(), model.getLength(),
                model.getCupSize(), model.getMeasures());
    }

    /**
     * Private method returning the web target for this service.
     * It builds a client and add a target to it, using the apiUrl from the yml file.
     */
    private ResteasyWebTarget getTarget() {
        final ResteasyClient client = new ResteasyClientBuilder().build();
        return client.target(env.getProperty("apiUrl"));
    }
}
