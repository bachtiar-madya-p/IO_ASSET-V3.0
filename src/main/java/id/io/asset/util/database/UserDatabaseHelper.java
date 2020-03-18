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
package id.io.asset.util.database;

import id.io.asset.manager.EncryptionManager;
import id.io.asset.model.DepartmentMemberModel;
import id.io.asset.model.DepartmentModel;
import id.io.asset.model.MemberLevelModel;
import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

public class UserDatabaseHelper extends BaseDatabaseHelper {

    public UserDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public UserModel login(String username, String password) {
        log.debug(UserDatabaseHelper.class.getName(), "- login");

        UserModel user = new UserModel();
        final String sql = "SELECT user.userid, user.username, user.alias, departmentmember.memberid, departmentmember.membercode, departmentmember.membername, departmentmember.email, departmentmember.imageaddress, departmentmember.description, departmentmember.levelid, departmentmember.departmentid, IF(departmentmember.isadmin , 'true', 'false') isadmin, IF(departmentmember.isactive ,'true','false') isactive\n"
                + "FROM user \n"
                + "INNER JOIN departmentmember ON user.memberid = departmentmember.memberid\n"
                + "WHERE user.username = :username AND user.password = :password;";
        int row = 0;
        try (Handle handle = getHandle()) {

            user = handle.createQuery(sql)
                    .bind(ConstantHelper.USER_USERNAME, username)
                    .bind(ConstantHelper.USER_PASSWORD, password).mapToBean(UserModel.class).first();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - failedLogin " + ex);
        }
        return user;
    }

    public List<UserModel> list() {
        List<UserModel> list = new ArrayList<>();

        log.debug(UserDatabaseHelper.class.getName(), " - getListUser");

        final String sql = "SELECT user.userid, user.username, user.password, user.alias, departmentmember.memberid, departmentmember.membercode, departmentmember.membername, departmentmember.email, departmentmember.imageaddress, departmentmember.description, departmentmember.levelid, departmentmember.departmentid, departmentmember.isadmin, IF(departmentmember.isactive , 'true', 'false') isactive "
                + "FROM user "
                + "INNER JOIN departmentmember ON user.memberid = departmentmember.memberid;";

        try (Handle h = getHandle()) {
            list = h.createQuery(sql).mapToBean(UserModel.class).list();
        } catch (Exception ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorGetListUser " + ex);
        }
        return list;
    }

    public UserModel getById(String userId) {

        UserModel user = new UserModel();

        log.debug(UserDatabaseHelper.class.getName(), " - getUser");

        final String sql = "SELECT user.userid, user.username, user.password, user.alias, departmentmember.memberid, departmentmember.membercode, departmentmember.membername, departmentmember.email, departmentmember.imageaddress, departmentmember.description, departmentmember.levelid, departmentmember.departmentid, IF(departmentmember.isadmin ,\n"
                + "	'true',\n"
                + "	'false') isadmin, IF(departmentmember.isactive , 'true', 'false') isactive "
                + "FROM user "
                + "INNER JOIN departmentmember ON user.memberid = departmentmember.memberid "
                + "WHERE user.userid = :userid;";

        try (Handle h = getHandle()) {
            user = h.createQuery(sql).bind(ConstantHelper.USER_USERID, userId).mapToBean(UserModel.class).first();
        } catch (Exception ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorGetUser " + ex);
        }

        return user;
    }

    public UserModel getByUsername(String username) {

        UserModel user = new UserModel();

        log.debug(UserDatabaseHelper.class.getName(), " - getUser");

        final String sql = "SELECT user.userid, user.username, user.password, user.alias, departmentmember.memberid, departmentmember.membercode, departmentmember.membername, departmentmember.email, departmentmember.imageaddress, departmentmember.description, departmentmember.levelid, departmentmember.departmentid, IF(departmentmember.isadmin ,\n"
                + "	'true',\n"
                + "	'false') isadmin, IF(departmentmember.isactive , 'true', 'false') isactive "
                + "FROM user "
                + "INNER JOIN departmentmember ON user.memberid = departmentmember.memberid "
                + "WHERE user.username = :username;";

        try (Handle h = getHandle()) {
            user = h.createQuery(sql).bind(ConstantHelper.USER_USERNAME, username).mapToBean(UserModel.class).first();
        } catch (Exception ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorGetUser " + ex);
        }

        return user;
    }

    public String getMemberId(String userId) {

        String memberId = null;
        UserModel user = new UserModel();

        log.debug(UserDatabaseHelper.class.getName(), " - getMemberId");

        final String sql = "SELECT user.userid, user.username, user.password, user.alias, departmentmember.memberid, departmentmember.membercode, departmentmember.membername, departmentmember.email, departmentmember.imageaddress, departmentmember.description, departmentmember.levelid, departmentmember.departmentid, departmentmember.isadmin, IF(departmentmember.isactive , 'true', 'false') isactive "
                + "FROM user "
                + "INNER JOIN departmentmember ON user.memberid = departmentmember.memberid "
                + "WHERE user.userid = :userid;";

        try (Handle h = getHandle()) {
            user = h.createQuery(sql).bind(ConstantHelper.USER_USERID, userId).mapToBean(UserModel.class).first();
            memberId = user.getMemberid();
        } catch (Exception ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorGetMemberId " + ex);
        }

        return memberId;
    }

    public boolean validateUsername(String username) {

        boolean isValid = false;
        int count = 0;

        log.debug(UserDatabaseHelper.class.getName(), " - getUsername");

        final String sql = "SELECT COUNT(1) FROM `user` WHERE username = :username;";

        try (Handle h = getHandle()) {
            count = h.createQuery(sql).bind(ConstantHelper.USER_USERNAME, username).mapTo(Integer.class).findOnly();
            if (count == 1) {
                isValid = true;
            }
        } catch (Exception ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorGetUsername " + ex);
        }

        return isValid;
    }

    public int create(UserModel model) {
        log.debug(UserDatabaseHelper.class.getName(), "- createUser");

        final String sql = "INSERT INTO `user` (userid, username, password, alias, memberid, isactive) VALUES( :userid, :username, :password, :alias, :memberid, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.USER_USERID, model.getUserid())
                    .bind(ConstantHelper.USER_USERNAME, model.getUsername())
                    .bind(ConstantHelper.USER_PASSWORD, model.getPassword())
                    .bind(ConstantHelper.USER_ALIAS, model.getAlias())
                    .bind(ConstantHelper.USER_MEMBERID, model.getMemberid())
                    .bind(ConstantHelper.USER_ISACTIVE, false).execute();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorCreateUser " + ex);
        }
        return row;
    }
         
    public int activate(String userId, boolean isActive) {
        log.debug(UserDatabaseHelper.class.getName(), "- activateUser");

        final String sql = "UPDATE user SET isactive = :isactive WHERE userid = :userid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.USER_USERID, userId)
                    .bind(ConstantHelper.USER_ISACTIVE, isActive).execute();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorActivateUser " + ex);
        }
        return row;
    }

    public boolean validateActivation(String username) {
        log.debug(UserDatabaseHelper.class.getName(), "- validateUser");

        boolean isActive = false;

        final String sql = "SELECT COUNT(1) FROM `user` WHERE username = :username; AND isactive = 1 ";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createQuery(sql).bind(ConstantHelper.USER_USERNAME, username).mapTo(Integer.class).findOnly();

            if (row == 1) {
                isActive = true;
            }

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorValidateUser " + ex);
        }
        return isActive;
    }

    public int changePassword(String username, String password) {
        log.debug(UserDatabaseHelper.class.getName(), "- changePassword");

        final String sql = "UPDATE user SET password = :password WHERE username = :username ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.USER_USERNAME, username)
                    .bind(ConstantHelper.USER_PASSWORD, password).execute();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorChangePassword " + ex);
        }
        return row;
    }

    public int delete(String userId) {

        log.debug(UserDatabaseHelper.class.getName(), "- deleteUser");

        UserModel users = getById(userId);
        final String userSql = "DELETE FROM user WHERE userid = :userid;";
        final String memberSql = "DELETE FROM departmentmember WHERE memberid= :memberid;";
        int userResult = 0;
        int memberResult = 0;
        try (Handle handle = getHandle()) {

            userResult = handle.createUpdate(userSql).bind(ConstantHelper.USER_USERID, userId).execute();
            if (userResult != 0) {
                memberResult = handle.createUpdate(memberSql).bind(ConstantHelper.USER_MEMBERID, users.getMemberid()).execute();
            }

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorDeleteUser " + ex);
        }
        return memberResult;
    }

    //updateUser
    public int updateUser(String userId, UserModel model) {
        log.debug(AssetDatabaseHelper.class.getName(), "- updateUser");
        final String updateDepartmentMember = "UPDATE departmentmember SET "
                + "membername= :membername, email= :email, imageaddress= :imageaddress, description= :description, "
                + "levelid= :levelid, departmentid= :departmentid WHERE memberid= :memberid;";
        int rows = 0;
        UserModel users = getById(userId);
        if (users != null) {
            try (Handle handle = getHandle()) {

                rows = handle.createUpdate(updateDepartmentMember)
                        .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, users.getMemberid())
                        .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERNAME, model.getMembername())
                        .bind(ConstantHelper.DEPARTMENTMEMBER_EMAIL, model.getEmail())
                        .bind(ConstantHelper.DEPARTMENTMEMBER_IMAGEADDRESS, model.getImageaddress())
                        .bind(ConstantHelper.DEPARTMENTMEMBER_DESCRIPTION, model.getDescription())
                        .bind(ConstantHelper.DEPARTMENTMEMBER_LEVELID, model.getLevelid())
                        .bind(ConstantHelper.DEPARTMENTMEMBER_DEPARTMENTID, model.getDepartmentid()).execute();

            } catch (SQLException ex) {
                log.error(AssetDatabaseHelper.class.getName(), " - errorUpdateUser " + ex);
            }
        }
        return rows;
    }
}
