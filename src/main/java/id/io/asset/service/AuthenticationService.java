/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author permadi
 */
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
        JSONObject response = new JSONObject();
        try {
            return Response.ok(authenticationController.authenticate(new JSONObject(authRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_UNAUTHORIZED);
            response.put(ConstantHelper.HTTP_REASON, "wrong_username_or_password");
            response.put(ConstantHelper.HTTP_MESSAGE, "Wrong Username or Password");

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }

    @POST
    @Path("/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPassword(String jsonRequest) {
        JSONObject response = new JSONObject();
        try {

            return Response.ok(authenticationController.createPassword(new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_password");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Password");

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
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
