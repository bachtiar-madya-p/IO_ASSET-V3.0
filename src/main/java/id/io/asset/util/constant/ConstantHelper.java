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
package id.io.asset.util.constant; 

public class ConstantHelper {

    // REST Endpoints
    public static final String HTTP_STATUS_CODE         = "statusCode";
    public static final String HTTP_RESPONSE            = "response";
    public static final String HTTP_REASON              = "reason";
    public static final String HTTP_CODE                = "code";
    public static final String HTTP_MESSAGE             = "message";
    public static final String HTTP_SUCCESSFUL          = "successful";

    // Database Tables
    public static final String CONFIG_DB_TABLE          = "configuration";
    public static final String CONFIG_DB_COL_ID         = "configuration.id";
    public static final String CONFIG_DB_COL_KEY        = " configuration.key";
    public static final String CONFIG_DB_COL_VALUE      = "configuration.value";

    //EMAIL CONFIGURATION
    public static final String EMAIL_HOSTNAME                   = "EMAIL_HOSTNAME";
    public static final String EMAIL_PORT                       = "EMAIL_PORT";
    public static final String EMAIL_USERNAME                   = "EMAIL_USERNAME";
    public static final String EMAIL_PASSWORD                   = "EMAIL_PASSWORD";
    public static final String EMAIL_ENABLE                     = "EMAIL_ENABLE";
    public static final String EMAIL_SERVER_NAME                = "EMAIL_SERVER_NAME";
    public static final String EMAIL_AUTHENTICATION_REQUIRED    = "EMAIL_AUTHENTICATION_REQUIRED";
    public static final String EMAIL_SMTP_REQUIRED              = "EMAIL_SMTP_REQUIRED";

    //OTP CONFIGURATION
    public static final String OTP_EXPIRY               = "OTP_EXPIRY";
    public static final String OTP_LENGTH               = "OTP_LENGTH";
    public static final String OTP_EMAIL_SUBJECT        = "OTP_EMAIL_SUBJECT";
    public static final String OTP_EMAIL_CONTENT        = "OTP_EMAIL_CONTENT";

    //RESET PASSWORD CONFIGURATION
    public static final String PASSWORD_RESET_EMAIL_CONTENT         = "PASSWORD_RESET_EMAIL_CONTENT";
    public static final String PASSWORD_RESET_EMAIL_SUBJECT         = "PASSWORD_RESET_EMAIL_SUBJECT";
    public static final String PASSWORD_RESET_TOKEN_EXPIRY          = "PASSWORD_RESET_TOKEN_EXPIRY";
    public static final String PASSWORD_RESET_TOKEN_LENGTH          = "PASSWORD_RESET_TOKEN_LENGTH";
    
    
     //USER RESPONSE CONFIGURATION
    
    public static final String USER_USERID          = "userid";
    public static final String USER_USERNAME        = "username";
    public static final String USER_ALIAS           = "alias";
    public static final String USER_MEMBERID        = "memberid";
    public static final String USER_MEMBERCODE      = "membercode";
    public static final String USER_MEMBERNAME      = "membername";
    public static final String USER_EMAIL           = "email";
    public static final String USER_IMAGEADDRESSES  = "imageaddress";
    public static final String USER_LEVELID         = "levelid";
    public static final String USER_DEPARTMENTID    = "departmentid";
    public static final String USER_DESCRIPTION     = "description";
    public static final String USER_ISADMIN         = "isadmin";

    //ASSET RESPONSE CONFIGURATION
    public static final String ASSET_ID             = "assetid";
    public static final String ASSET_ASSETCODE       = "assetcode";
    public static final String ASSET_ASSETNAME       = "assetname";
    public static final String ASSET_TYPEID          = "typeid";
    public static final String ASSET_MANUFACTURE     = "manufacture";
    public static final String ASSET_MODEL           = "model";
    public static final String ASSET_VENDORID        = "vendorid";
    public static final String ASSET_NOTE            = "note";
    public static final String ASSET_CREATEDT        = "createdt";
    
}
