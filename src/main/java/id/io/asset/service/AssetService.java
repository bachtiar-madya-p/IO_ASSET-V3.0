/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.AssetController;
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
 * @author untung bagus purnomo
 */
@Path("asset")
@Produces(MediaType.APPLICATION_JSON)
public class AssetService extends BaseService {

    private AssetController assetsController;

    public AssetService() {
        this.assetsController = new AssetController();
    }

    @GET
    public Response assetList() {
        return Response.ok(assetsController.assetList()).build();
    }

    @GET
    @Path("/{assetId}")
    public Response assetById(@PathParam("assetId") String assetId) {
        return Response.ok(assetsController.getAsset(assetId)).build();
    }

    //create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            response = assetsController.create(new JSONObject(jsonRequest));
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_asset");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Asset cause :" + ex.getMessage());
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
            return Response.ok(assetsController.update(assetId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_asset");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Asset cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    //Delete
    @DELETE
    @Path("/{assetId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("assetId") String assetId) {

        JSONObject response = assetsController.delete(assetId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }
    
   
    

}
