/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.ConfigurationController;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
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

        return Response.ok(configurationController.createConfig(new JSONObject(jsonRequest)).toString()).build();
        //return Response.ok(configurationController.getListConfiguration().toString()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putConfig(String jsonRequest) {

        return Response.ok(configurationController.updateConfig(new JSONObject(jsonRequest)).toString()).build();
        //return Response.ok(configurationController.getListConfiguration().toString()).build();
    }

}
