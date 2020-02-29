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
package id.io.asset.controller;

import id.io.asset.model.DepartmentModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.DepartmentDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

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
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.DEPARTMENT_DEPARTMENTCODE));

            DepartmentModel model = new DepartmentModel();
            model.setDepartmentid(uuid.toString());
            model.setDepartmentcode(json.getString(ConstantHelper.DEPARTMENT_DEPARTMENTCODE));
            model.setDepartmentname(json.getString(ConstantHelper.DEPARTMENT_DEPARTMENTNAME));
            model.setDescription(json.getString(ConstantHelper.DEPARTMENT_DESCRIPTION));

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
            model.setDepartmentcode(json.getString(ConstantHelper.DEPARTMENT_DEPARTMENTCODE));
            model.setDepartmentname(json.getString(ConstantHelper.DEPARTMENT_DEPARTMENTNAME));
            model.setDescription(json.getString(ConstantHelper.DEPARTMENT_DESCRIPTION));
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
            departmentDatabaseHelper.activate(departmentId, json.getBoolean(ConstantHelper.DEPARTMENT_ISACTIVE));

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
