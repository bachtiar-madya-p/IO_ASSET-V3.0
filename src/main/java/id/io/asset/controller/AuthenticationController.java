/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;


import id.io.asset.manager.EncryptionManager;
import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.UserDatabaseHelper;
import java.sql.SQLException;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class AuthenticationController extends BaseController {
    private String id;
    private String issuer;
    private String subject;
    private  long ttlMillis;
    private UserDatabaseHelper userDatabaseHelper;
    

    public AuthenticationController(String id, String issuer, String subject, long ttlMillis) {
        log = getLogger(this.getClass());
        this.userDatabaseHelper = new UserDatabaseHelper();
        this.createJWT(id, issuer, subject, ttlMillis);
        this.id = id;
        this.issuer = issuer;
        this.subject = subject;
        this.ttlMillis= ttlMillis;
        
    }

    public JSONObject authenticate(JSONObject jsonRequest) {
        JSONObject json = new JSONObject();
        json.getString(SECRET_KEY);
        String decrypPassword = EncryptionManager.encrypt(jsonRequest.getString("password"));

        UserModel user = new UserModel();
        try {
            user = userDatabaseHelper.login(jsonRequest.getString("username"), decrypPassword);
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.USER_USERID, user.getUserid());
            json.put(ConstantHelper.USER_USERNAME, user.getUsername());
            json.put(ConstantHelper.USER_MEMBERID, user.getMemberid());
            json.put(ConstantHelper.USER_MEMBERCODE, user.getMembercode());
            json.put(ConstantHelper.USER_MEMBERNAME, user.getMembername());
            json.put(ConstantHelper.USER_EMAIL, user.getEmail());
            json.put(ConstantHelper.USER_IMAGEADDRESSES, user.getImageaddress());
            json.put(ConstantHelper.USER_LEVELID, user.getLevelid());
            json.put(ConstantHelper.USER_DEPARTMENTID, user.getDepartmentid());
            json.put(ConstantHelper.USER_DESCRIPTION, user.getDescription());
            json.put(ConstantHelper.USER_ISADMIN, user.isIsadmin());
            json.put(ConstantHelper.USER_SESSION_JWT, createJWT(id, issuer, subject, ttlMillis));
        } catch (Exception ex) {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_UNAUTHORIZED);
            json.put(ConstantHelper.HTTP_REASON, "wrong_username_or_password");
            json.put(ConstantHelper.HTTP_MESSAGE, "Wrong Username or Password");
        }

        return json;
    }

    public JSONObject createPassword(JSONObject jsonRequest) {

        JSONObject json = new JSONObject();

        String username = jsonRequest.getString("username");
        String password = jsonRequest.getString("password");

        UserModel user = userDatabaseHelper.getByUsername(username);

        if (user.getUserid() != null) {
            String encryptedPassword = EncryptionManager.encrypt(password);
            userDatabaseHelper.changePassword(username, encryptedPassword);

            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
            json.put(ConstantHelper.HTTP_REASON, "create_passwordt_successful");
            json.put(ConstantHelper.HTTP_MESSAGE, "Create Password Successful!");

        } else {
            json.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_BAD_REQUEST);
            json.put(ConstantHelper.HTTP_REASON, "error_create_passwoed");
            json.put(ConstantHelper.HTTP_MESSAGE, "Error Create Password");
        }
        return json;
    }
}
