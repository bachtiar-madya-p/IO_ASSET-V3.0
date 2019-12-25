/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.DepartmentMemberDatabaseHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import id.io.asset.util.database.UserDatabaseHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class UserController extends BaseController {

    private UserDatabaseHelper userDatabaseHelper;
    private DepartmentMemberDatabaseHelper memberDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;

    public UserController() {
        this.userDatabaseHelper = new UserDatabaseHelper();
        this.memberDatabaseHelper = new DepartmentMemberDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }

    public List<UserModel> userList() {
        List<UserModel> list = userDatabaseHelper.list();
        return list;
    }

    public UserModel userById(String userId) {

        UserModel user = userDatabaseHelper.getById(userId);
        return user;
    }

    public boolean validateUsername(String username) {

        boolean result = false;

        boolean isValid = userDatabaseHelper.validateUsername(username);
        if (!isValid) {
            result = true;
        }
        return result;
    }

    public JSONObject validateActivation(String username) {
        JSONObject json = new JSONObject();

        boolean isValid = userDatabaseHelper.validateActivation(username);
        if (isValid) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_MESSAGE, isValid);
        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "account_inactive!!");
            json.put(ConstantHelper.HTTP_MESSAGE, isValid);
        }
        return json;
    }

    public JSONObject create(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID memberId = uuidGenerator.generateUUID(json.getString("membercode"));
            UUID userId = uuidGenerator.generateUUID(json.getString("username"));

            UserModel model = new UserModel();
            model.setUserid(userId.toString());
            model.setUsername(json.getString("username"));
            model.setAlias(json.getString("alias"));
            model.setMemberid(memberId.toString());
            model.setMembercode(json.getString("membercode"));
            model.setMembername(json.getString("membername"));
            model.setEmail(json.getString("email"));
            model.setImageaddress(json.getString("imageaddress"));
            model.setDescription(json.getString("description"));
            model.setLevelid(json.getString("levelid"));
            model.setDepartmentid(json.getString("departmentid"));

            memberDatabaseHelper.create(model);
            userDatabaseHelper.create(model);
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_user_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create User Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create User!!!");
        }
        return response;
    }

    public JSONObject activateUser(String userId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            userDatabaseHelper.activate(userId, json.getBoolean("isactive"));
            String memberId = userDatabaseHelper.getMemberId(userId);
            memberDatabaseHelper.activate(memberId, json.getBoolean("isactive"));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_user_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate User Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_user");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate User");
        }
        return response;
    }

    public JSONObject asAdmin(String userId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            String memberId = userDatabaseHelper.getMemberId(userId);
            memberDatabaseHelper.asAdmin(memberId, json.getBoolean("isadmin"));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_admin_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate Admin Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_admin");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate Admin");
        }
        return response;
    }

}
