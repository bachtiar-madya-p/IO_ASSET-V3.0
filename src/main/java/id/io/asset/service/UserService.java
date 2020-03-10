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

import id.io.asset.controller.OtpController;
import id.io.asset.controller.UserController;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.ConfigurationDatabaseHelper;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends BaseService {

    private UserController userController;
    private ConfigurationDatabaseHelper configurationDatabaseHelper;
    private OtpController otpController;

    public UserService() {
        this.userController = new UserController();
        this.configurationDatabaseHelper = new ConfigurationDatabaseHelper();
        this.otpController = new OtpController();
    }

    @GET
    public Response userList() {
        return Response.ok(userController.userList()).build();
    }

    @GET
    @Path("/{userId}")
    public Response userById(@PathParam("userId") String userId) {
        return Response.ok(userController.userById(userId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String jsonRequest) {
        JSONObject response = new JSONObject();

        JSONObject json = new JSONObject(jsonRequest);
        boolean validateUsername = userController.validateUsername(json.getString(ConstantHelper.USER_USERNAME));
        if (validateUsername) {
            try {
                response = userController.create(new JSONObject(jsonRequest));
                JSONObject otpConfig = configurationDatabaseHelper.getOtpEmailConfiguration();
                String otp = otpController.generateOtp(Integer.parseInt(otpConfig.getString(ConstantHelper.OTP_LENGTH)));
                otpController.saveOtpLog(json.getString(ConstantHelper.USER_EMAIL), otp);

            } catch (JSONException ex) {
                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
                response.put(ConstantHelper.HTTP_REASON, "error_create_user");
                response.put(ConstantHelper.HTTP_MESSAGE, "Error Create User!!!");
            }
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Username Already in use!!");
        }

        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("userId") String userId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(userController.update(userId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update User cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    @POST
    @Path("/activate/{userId}")
    public Response activateUser(@PathParam("userId") String userId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {

            return Response.ok(userController.activateUser(userId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_activate_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate user");

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }

    @POST
    @Path("/toadmin/{userId}")
    public Response toAdmin(@PathParam("userId") String userId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(userController.asAdmin(userId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_change_user_type");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Change User Type");

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
    
    @DELETE
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("userId") String userId) {

        JSONObject response = userController.delete(userId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }
}
