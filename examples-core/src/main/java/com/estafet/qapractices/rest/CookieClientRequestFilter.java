package com.estafet.qapractices.rest;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petar Petrov on 07-Nov-17.
 */
@Provider
public class CookieClientRequestFilter implements ClientRequestFilter {
    private Cookie cookie;

    public CookieClientRequestFilter(Cookie cookie) {
        super();
        this.cookie = cookie;
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        List<Object> cookies = new ArrayList<Object>();
        cookies.add(this.cookie);
        System.out.println("pe6o " + cookie.getName());
        System.out.println("pe6o " + cookie.getValue());
        clientRequestContext.getHeaders().put("Authorization", cookies);
    }
}
