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

import id.io.asset.controller.MemberLevelController;
import id.io.asset.util.constant.ConstantHelper;
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

@Path("member-level")
@Produces(MediaType.APPLICATION_JSON)
public class MemberLevelService extends BaseService {

    private MemberLevelController userLevelController;

    public MemberLevelService() {
        this.userLevelController = new MemberLevelController();
    }

    @GET
    public Response levelList() {
        return Response.ok(userLevelController.userLevelList()).build();
    }

    @GET
    @Path("/{levelId}")
    public Response levelById(@PathParam("levelId") String levelId) {
        return Response.ok(userLevelController.userLevelById(levelId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMemberLevel(String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            response = userLevelController.createMemberLevel(new JSONObject(jsonRequest));
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_member_level");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Member Level cause :" + ex.getMessage());
        }

        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }

    @PUT
    @Path("/{levelid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMemberLevel(@PathParam("levelid") String levelId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(userLevelController.updateMemberLevel(levelId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_member_level");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Member Level cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    @POST
    @Path("/activate/{levelid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activateMemberLevel(@PathParam("levelid") String levelId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(userLevelController.activateMemberLevel(levelId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_activate_member_level");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate Member Level cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    @DELETE
    @Path("/{levelid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMemberLevel(@PathParam("levelid") String levelId) {

        JSONObject response = userLevelController.remove(levelId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }

}
