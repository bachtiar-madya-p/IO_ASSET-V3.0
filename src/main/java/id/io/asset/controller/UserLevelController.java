/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.service.model.UserLevelModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import id.io.asset.util.database.UserLevelDatabaseHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class UserLevelController extends BaseController {

    private UserLevelDatabaseHelper userLevelDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;

    public UserLevelController() {
        this.userLevelDatabaseHelper = new UserLevelDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }

    public List<UserLevelModel> userLevelList() {
        List<UserLevelModel> levelList = userLevelDatabaseHelper.levelList();
        return levelList;
    }

    public UserLevelModel userLevelById(String levelId) {

        UserLevelModel level = userLevelDatabaseHelper.getLevelById(levelId);
        return level;
    }

    public JSONObject createMemberLevel(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID uuid = uuidGenerator.generateUUID(json.getString("levelcode"));

            UserLevelModel model = new UserLevelModel();
            model.setLevelid(uuid.toString());
            model.setLevelcode(json.getString("levelcode"));
            model.setLevelname(json.getString("levelname"));
            model.setDescription(json.getString("description"));

            userLevelDatabaseHelper.create(model);

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "create_member_level_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Create MemberLevel Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_create_member_level");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Create MemberLevel : No such MemberLevel");
        }
        return response;
    }

    public JSONObject updateMemberLevel(String levelId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            UserLevelModel model = new UserLevelModel();
            model.setLevelcode(json.getString("levelcode"));
            model.setLevelname(json.getString("levelname"));
            model.setDescription(json.getString("description"));

            userLevelDatabaseHelper.update(levelId, model);

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "update_member_level_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Update MemberLevel Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_update_member_level");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Update MemberLevel : No such MemberLevel");
        }
        return response;
    }

    public JSONObject activateMemberLevel(String levelId, JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {

            userLevelDatabaseHelper.activate(levelId, json.getBoolean("isactive"));

            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            response.put(ConstantHelper.HTTP_REASON, "activate/inactivate_member_level_successful");
            response.put(ConstantHelper.HTTP_MESSAGE, "Activate/Inactivate MemberLevel Successful!");
        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            response.put(ConstantHelper.HTTP_REASON, "error_activate/inactivate_member_level");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Activate/Inactivate MemberLevel : No such MemberLevel");
        }
        return response;
    }

    public JSONObject remove(String key) {
        JSONObject json = new JSONObject();
        int result = userLevelDatabaseHelper.remove(key);
        if (result == 1) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "delete_member_level_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Delete MemberLevel Successful!");

        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_delete_member_level");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Delete MemberLevel");
        }
        return json;
    }

}
