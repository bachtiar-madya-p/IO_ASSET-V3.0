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

import id.io.asset.controller.ProvinceController;
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

@Path("province")
@Produces(MediaType.APPLICATION_JSON)
public class ProvinceService extends BaseService {
    
    private ProvinceController provinceController;
    
    public ProvinceService (){
        this.provinceController = new ProvinceController();
    }
    
    //list
    @GET
    public Response provinceList(){
        return Response.ok(provinceController.provinceList()).build();
    }
    
    @GET
    @Path("/{provinceId}")
    public Response provinceById(@PathParam("provinceId") String provinceId){
        return Response.ok(provinceController.getProvince(provinceId)).build();
    }
    
    //create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonRequest){
        JSONObject response = new JSONObject();
        try{
            response = provinceController.create(new JSONObject(jsonRequest));
        }catch (JSONException ex){
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_province");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Province cause :" + ex.getMessage());
        }
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    //update
    @PUT
    @Path("/{provinceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("provinceId") String provinceId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(provinceController.update(provinceId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_province");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Province cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }
    
    //activate
    @POST
    @Path("/activate/{provinceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(@PathParam("provinceId") String provinceId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(provinceController.activate(provinceId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_activate_province");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate Province cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    //delete
    @DELETE
    @Path("/{provinceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("provinceId") String provinceId) {

        JSONObject response = provinceController.delete(provinceId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }
}
