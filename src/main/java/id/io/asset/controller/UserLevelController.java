/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.service.model.UserLevelModel;
import id.io.asset.util.database.UserLevelDatabaseHelper;
import java.util.List;

/**
 *
 * @author permadi
 */
public class UserLevelController extends BaseController {

    private UserLevelDatabaseHelper userLevelDatabaseHelper;

    public UserLevelController() {
        this.userLevelDatabaseHelper = new UserLevelDatabaseHelper();
    }

    public List<UserLevelModel> userLevelList() {
        List<UserLevelModel> levelList = userLevelDatabaseHelper.levelList();
        return levelList;
    }

    public UserLevelModel userLevelById(String levelId) {

        UserLevelModel level = userLevelDatabaseHelper.getLevelById(levelId);
        return level;
    }

}
