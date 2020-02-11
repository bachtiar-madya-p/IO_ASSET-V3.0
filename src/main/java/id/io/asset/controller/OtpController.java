/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import id.io.asset.manager.EncryptionManager;
import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.database.ConfigurationDatabaseHelper;
import id.io.asset.util.database.OtpLogDatabaseHelper;
import id.io.asset.util.database.UserDatabaseHelper;
import id.io.asset.util.email.EmailHelper;
import java.util.Random;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class OtpController extends BaseController {

    private OtpLogDatabaseHelper otpDatabaseHelper;
    private UserDatabaseHelper userDatabaseHelper;
    private ConfigurationDatabaseHelper configDatabaseHelper;

    public OtpController() {
        this.otpDatabaseHelper = new OtpLogDatabaseHelper();
        this.userDatabaseHelper = new UserDatabaseHelper();
        this.configDatabaseHelper = new ConfigurationDatabaseHelper();

    }

    public JSONObject saveOtpLog(String email, String otp) {
        JSONObject response = new JSONObject();
        JSONObject emailConfig = configDatabaseHelper.getEmailConfiguration();
        JSONObject otpConfig = configDatabaseHelper.getOtpEmailConfiguration();

        if (otpDatabaseHelper.saveOtpLog(email, otp) != 0) {

            if (Boolean.parseBoolean(emailConfig.getString(ConstantHelper.EMAIL_ENABLE))) {
                String recipient = email;
                String subject = otpConfig.getString(ConstantHelper.OTP_EMAIL_SUBJECT);
                String otpExpiry = otpConfig.getString(ConstantHelper.OTP_EXPIRY);
                String content = generateContent(otp, otpExpiry);

                String host = emailConfig.getString(ConstantHelper.EMAIL_HOSTNAME);
                String port = emailConfig.getString(ConstantHelper.EMAIL_PORT);
                String username = emailConfig.getString(ConstantHelper.EMAIL_USERNAME);

                String encryptedPassword = emailConfig.getString(ConstantHelper.EMAIL_PASSWORD);
                String password = EncryptionManager.decrypt(encryptedPassword);

                boolean isSecure = Boolean.parseBoolean(emailConfig.getString(ConstantHelper.EMAIL_SMTP_REQUIRED));
                boolean isAuth = Boolean.parseBoolean(emailConfig.getString(ConstantHelper.EMAIL_AUTHENTICATION_REQUIRED));


                EmailHelper.sendMail(host, port, isSecure, emailConfig.getString(ConstantHelper.EMAIL_SERVER_NAME), recipient, subject, content, username, password, isAuth);

                response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_OK);
                response.put(ConstantHelper.HTTP_REASON, "otp_verification_email_successfull!");
                response.put(ConstantHelper.HTTP_MESSAGE, "Successfull Send OTP Verification Email");
            }

        } else {
            response.put(ConstantHelper.HTTP_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.put(ConstantHelper.HTTP_REASON, "error_send_otp_verification");
            response.put(ConstantHelper.HTTP_MESSAGE, "Error Send OTP Verification!!!");
        }
        return response;
    }

    private String generateContent(String otp, String expiry) {
        JSONObject otpConfig = configDatabaseHelper.getOtpEmailConfiguration();
        String content = otpConfig.getString(ConstantHelper.OTP_EMAIL_CONTENT).replace("${otp}", otp).replace("${otpExpiry}", expiry);

        return content;
    }

    public String generateOtp(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        String result = String.valueOf(otp);
        return result;
    }

}
