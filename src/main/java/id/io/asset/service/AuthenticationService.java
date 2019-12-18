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
import id.io.asset.filter.SessionFilter;
import id.io.asset.model.Principal;
import id.io.asset.service.model.AuthenticationRequest;

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
    public Response login(AuthenticationRequest authRequest) {

        boolean isAuth = authenticationController.authenticate(authRequest.getUsername(), authRequest.getPassword());

        if (isAuth) {

            clearSession();
            HttpSession session = request.getSession(true);
            Principal principal = new Principal(authRequest.getUsername());
            session.setAttribute(Principal.class.getCanonicalName(), principal);
            session.setAttribute(SessionFilter.SESSION_KEY, principal);
            return getSuccessResponse();

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
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
