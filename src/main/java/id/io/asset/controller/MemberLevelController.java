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

import id.io.asset.model.MemberLevelModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.UUIDGeneratorHelper;
import id.io.asset.util.database.MemberLevelDatabaseHelper;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class MemberLevelController extends BaseController {

    private MemberLevelDatabaseHelper userLevelDatabaseHelper;
    private UUIDGeneratorHelper uuidGenerator;

    public MemberLevelController() {
        this.userLevelDatabaseHelper = new MemberLevelDatabaseHelper();
        this.uuidGenerator = new UUIDGeneratorHelper();
    }

    public List<MemberLevelModel> userLevelList() {
        List<MemberLevelModel> levelList = userLevelDatabaseHelper.levelList();
        return levelList;
    }

    public MemberLevelModel userLevelById(String levelId) {

        MemberLevelModel level = userLevelDatabaseHelper.getLevelById(levelId);
        return level;
    }

    public JSONObject createMemberLevel(JSONObject json) {
        JSONObject response = new JSONObject();
        if (json.length() != 0) {
            UUID uuid = uuidGenerator.generateUUID(json.getString(ConstantHelper.MEMBERLEVEL_LEVELCODE));

            MemberLevelModel model = new MemberLevelModel();
            model.setLevelid(uuid.toString());
            model.setLevelcode(json.getString(ConstantHelper.MEMBERLEVEL_LEVELCODE));
            model.setLevelname(json.getString(ConstantHelper.MEMBERLEVEL_LEVELNAME));
            model.setDescription(json.getString(ConstantHelper.MEMBERLEVEL_DESCRIPTION));

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

            MemberLevelModel model = new MemberLevelModel();
            model.setLevelcode(json.getString(ConstantHelper.MEMBERLEVEL_LEVELCODE));
            model.setLevelname(json.getString(ConstantHelper.MEMBERLEVEL_LEVELNAME));
            model.setDescription(json.getString(ConstantHelper.MEMBERLEVEL_DESCRIPTION));

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

            userLevelDatabaseHelper.activate(levelId, json.getBoolean(ConstantHelper.MEMBERLEVEL_ISACTIVE));

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
