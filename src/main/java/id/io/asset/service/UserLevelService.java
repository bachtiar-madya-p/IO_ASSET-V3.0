/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.UserLevelController;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("userlevel")
@Produces(MediaType.APPLICATION_JSON)
public class UserLevelService extends BaseService {
    
    private UserLevelController userLevelController;
    
    public UserLevelService() {
        this.userLevelController = new UserLevelController();
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
    
}
