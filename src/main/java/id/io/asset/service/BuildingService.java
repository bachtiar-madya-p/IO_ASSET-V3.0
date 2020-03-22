/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.BuildingController;
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

/**
 *
 * @author user
 */
@Path("building")
@Produces(MediaType.APPLICATION_JSON)
public class BuildingService extends BaseService {
    
    private BuildingController buildingController;
    
    public BuildingService (){
        this.buildingController = new BuildingController();
    }
    
    //create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create (String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            response = buildingController.create(new JSONObject(jsonRequest));
        }catch (JSONException ex){
            response.put(ConstantHelper.HTTP_STATUS_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_building");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Building Cause :" + ex.getMessage());
        }
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    //delete
    @DELETE
    @Path("/{buildingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("buildingId") String buildingId) {
        JSONObject response = buildingController.delete(buildingId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    
    //update
    @PUT
    @Path("/{buildingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("buildingId") String buildingId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(buildingController.update(buildingId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_building");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Building cause :" + ex.getMessage());
            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
   
    //activate
    @POST
    @Path("/activate/{buildingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(@PathParam("buildingId") String buildingId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(buildingController.activate(buildingId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_activate_building");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate Building cause :" + ex.getMessage());
            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
    
    //list
    @GET
    public Response buildingList(){
        return Response.ok(buildingController.buildingList()).build();
    }
    
    @GET
    @Path("/{buildingId}")
    public Response buildingById(@PathParam("buildingId") String buildingId){
        return Response.ok(buildingController.getBuilding(buildingId)).build();
    }
}
