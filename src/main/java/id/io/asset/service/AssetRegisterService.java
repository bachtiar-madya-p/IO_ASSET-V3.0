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

import id.io.asset.controller.AssetRegisterController;
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

@Path("assetregister")
@Produces(MediaType.APPLICATION_JSON)
public class AssetRegisterService extends BaseService{
    
    private AssetRegisterController assetRegisterController;
    
    public AssetRegisterService() {
        this.assetRegisterController = new AssetRegisterController();
    }
    
    //create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonRequest) {
        JSONObject response = new JSONObject();
        try{
            response = assetRegisterController.create(new JSONObject(jsonRequest));
        }catch(JSONException ex){
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_asset_register");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Asset Register cause :" + ex.getMessage());
        }
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    //update
    @PUT
    @Path("/{assetId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("assetId") String assetId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(assetRegisterController.update(assetId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_asset_register");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Asset Register cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
    
    //Delete
    @DELETE
    @Path("/{assetId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("assetId") String assetId) {

        JSONObject response = assetRegisterController.delete(assetId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }
    
    //LIST
    @GET
    public Response assetList() {
        return Response.ok(assetRegisterController.assetList()).build();
    }
    
    //assetById
    @GET
    @Path("/{assetId}")
    public Response assetById(@PathParam("assetId") String assetId) {
        return Response.ok(assetRegisterController.getAsset(assetId)).build();
    }
}
