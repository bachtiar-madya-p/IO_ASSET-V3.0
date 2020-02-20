/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.DepartmentModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.DepartmentDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class DepartmentController extends BaseController {
    
    private DepartmentDatabaseHelper departmentDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;
    
    public DepartmentController() {
        departmentDatabaseHelper = new DepartmentDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
        
    }
     

    public List<DepartmentModel> departmentList() {
        List<DepartmentModel> departmentList = departmentDatabaseHelper.getList();
        return departmentList;
    }

    public DepartmentModel getDepartment(String departmentId) {
        DepartmentModel department = departmentDatabaseHelper.findById(departmentId);
        return department;
    }

    public JSONObject create(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID uuid = uuidGenerator.generateUUID(json.getString("departmentcode"));

            DepartmentModel model = new DepartmentModel();
            model.setDepartmentid(uuid.toString());
            model.setDepartmentcode(json.getString("departmentcode"));
            model.setDepartmentname(json.getString("departmentname"));
            model.setDescription(json.getString("description"));

            departmentDatabaseHelper.create(model);

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_department_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create Department Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_department");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create Department : No such Department");
        }
        return response;
    }

    public JSONObject update(String departmentId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            DepartmentModel model = new DepartmentModel();
            model.setDepartmentcode(json.getString("departmentcode"));
            model.setDepartmentname(json.getString("departmentname"));
            model.setDescription(json.getString("description"));
            departmentDatabaseHelper.update(departmentId, model);

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_department_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update Department Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_department");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update Department : No such Department");
        }
        return response;
    }

    public JSONObject activate(String departmentId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            departmentDatabaseHelper.activate(departmentId, json.getBoolean("isactive"));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_department_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate Department Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_department");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate Department : No such Department");
        }
        return response;
    }

    public JSONObject delete(String departmentId) {
        JSONObject json = new JSONObject();
        int result = departmentDatabaseHelper.delete(departmentId);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_department_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete Department Successful!");

        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_department");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete Department");
        }
        return json;
    }

}
