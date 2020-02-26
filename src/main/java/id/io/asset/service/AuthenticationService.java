/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
package id.io.asset.service;

import id.io.asset.controller.AuthenticationController;
import java.net.URI;
import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import id.io.asset.util.constant.ConstantHelper;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
@Path("authentication")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationService extends BaseService {

    private AuthenticationController authenticationController;

    public AuthenticationService() {
        this.authenticationController = new AuthenticationController();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String authRequest) {

        JSONObject jsonResponse = authenticationController.authenticate(new JSONObject(authRequest));
        if(jsonResponse.get(ConstantHelper.HTTP_CODE).equals(HttpStatus.SC_OK))
        {
            jsonResponse.remove(ConstantHelper.HTTP_CODE);//remove statusCode to response
        }
        return Response.status((!jsonResponse.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : jsonResponse.getInt(ConstantHelper.HTTP_CODE)).entity(jsonResponse.toString()).build();
    }

    @POST
    @Path("/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPassword(String jsonRequest) {
        Response response;
        JSONObject jsonResponse = new JSONObject();
        try {

            response = Response.ok(authenticationController.createPassword(new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            jsonResponse.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put(ConstantHelper.HTTP_REASON, "error_create_password");
            jsonResponse.put(ConstantHelper.HTTP_MESSAGE, "Error Create Password");

            response = Response.status((!jsonResponse.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : jsonResponse.getInt(ConstantHelper.HTTP_CODE)).entity(jsonResponse.toString()).build();
        }
        return response;
    }

    private void clearSession() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GET
    @Path("logout")
    @PermitAll
    public Response logout() {
        clearSession();
        return Response.temporaryRedirect(URI.create(request.getContextPath() + "/login")).build();
    }

    @GET
    @Path("session")
    @PermitAll
    public Response session() {
        return getSuccessResponse();
    }

}
