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

import id.io.asset.controller.ConfigurationController;
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

@Path("configurations")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigurationService extends BaseService {

    private ConfigurationController configurationController;

    public ConfigurationService() {
        log = getLogger(this.getClass());
        this.configurationController = new ConfigurationController();
    }

    @GET
    public Response list() {
        return Response.ok(configurationController.getListConfiguration().toString()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addConfig(String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            response = configurationController.createConfig(new JSONObject(jsonRequest));
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_configuration");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Configuration cause :" + ex.getMessage());
        }

        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putConfig(String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(configurationController.updateConfig(new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_configuration");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Configuration cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    @DELETE
    @Path("/remove/{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteConfig(@PathParam("key") String key) {

        JSONObject response = configurationController.removeConfig(key);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }

}
