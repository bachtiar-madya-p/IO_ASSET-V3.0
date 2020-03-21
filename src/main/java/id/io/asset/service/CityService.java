/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author user
 */

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
}
