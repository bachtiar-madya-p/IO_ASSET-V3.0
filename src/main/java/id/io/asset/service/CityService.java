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

import id.io.asset.controller.CityController;
import id.io.asset.controller.OtpController;
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

@Path("city")
@Produces(MediaType.APPLICATION_JSON)
public class CityService extends BaseService {
    
    private CityController cityController;
    
    public CityService (){
        this.cityController = new CityController();
    }
    
    //create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonRequest){
        JSONObject response = new JSONObject();
        try{
            response = cityController.create(new JSONObject(jsonRequest));
        }catch (JSONException ex){
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_city");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create City cause :" + ex.getMessage());
        }
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    //activate
    @POST
    @Path("/activate/{cityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(@PathParam("cityId") String cityId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(cityController.activate(cityId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_activate_city");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate City cause :" + ex.getMessage());
            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
    
    //delete
    @DELETE
    @Path("/{cityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("cityId") String cityId) {
        JSONObject response = cityController.delete(cityId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    //update
    @PUT
    @Path("/{cityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("cityId") String cityId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(cityController.update(cityId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_city");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update City cause :" + ex.getMessage());
            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
    
    //list
    @GET
    public Response cityList(){
        return Response.ok(cityController.cityList()).build();
    }
    
    @GET
    @Path("/{cityId}")
    public Response cityById(@PathParam("cityId") String cityId){
        return Response.ok(cityController.getCity(cityId)).build();
    }
}
