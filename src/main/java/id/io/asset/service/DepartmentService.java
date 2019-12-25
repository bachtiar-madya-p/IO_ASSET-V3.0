/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.service;

import id.io.asset.controller.DepartmentController;
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
 * @author permadi
 */
@Path("department")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentService extends BaseService {

    private DepartmentController departmentController;

    public DepartmentService() {
        this.departmentController = new DepartmentController();
    }

    @GET
    public Response departmetnList() {
        return Response.ok(departmentController.departmentList()).build();
    }

    @GET
    @Path("/{departmentId}")
    public Response departmentById(@PathParam("departmentId") String departmentId) {
        return Response.ok(departmentController.getDepartment(departmentId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            response = departmentController.create(new JSONObject(jsonRequest));
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_create_department");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Department cause :" + ex.getMessage());
        }

        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
    }

    @PUT
    @Path("/{departmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("departmentId") String departmentId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(departmentController.update(departmentId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_update_department");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Department cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    @POST
    @Path("/activate/{departmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(@PathParam("departmentId") String departmentId, String jsonRequest) {
        JSONObject response = new JSONObject();
        try {
            return Response.ok(departmentController.activate(departmentId, new JSONObject(jsonRequest)).toString()).build();
        } catch (JSONException ex) {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_activate_department");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate Department cause :" + ex.getMessage());

            return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                    ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();
        }

    }

    @DELETE
    @Path("/{departmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("departmentId") String departmentId) {

        JSONObject response = departmentController.delete(departmentId);
        return Response.status((!response.has(ConstantHelper.HTTP_CODE))
                ? HttpStatus.SC_OK : response.getInt(ConstantHelper.HTTP_CODE)).entity(response.toString()).build();

    }

}
