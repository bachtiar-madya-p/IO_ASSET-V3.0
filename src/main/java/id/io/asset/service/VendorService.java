/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.VendorController;
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
@Path("vendor")
@Produces(MediaType.APPLICATION_JSON)
public class VendorService extends BaseService{
    
    private VendorController vendorController;
    
    public VendorService() {
        this.vendorController = new VendorController();
    }
    
    //create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonRequest) {
        JSONObject response = new JSONObject();
        try{
            response = vendorController.create(new JSONObject(jsonRequest));
        }catch(JSONException ex){
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_vendor");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Vendor cause :" + ex.getMessage());
        }
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }
    //update
    @PUT
    @Path("/{vendorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("vendorId") String vendorId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(vendorController.update(vendorId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_vendor");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Vendor cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }
    }
    
    //Delete
    @DELETE
    @Path("/{vendorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("vendorId") String vendorId) {

        JSONObject response = vendorController.delete(vendorId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }
    
    //LIST
    @GET
    public Response vendorList() {
        return Response.ok(vendorController.vendorList()).build();
    }
    
    //assetById
    @GET
    @Path("/{vendorId}")
    public Response vendorById(@PathParam("vendorId") String vendorId) {
        return Response.ok(vendorController.getVendor(vendorId)).build();
    }
}
