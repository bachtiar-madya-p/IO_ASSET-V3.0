/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.UserModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
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
                    .bind("username", username)
                    .bind("password", password).mapToBean(UserModel.class).first();

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
            user = h.createQuery(sql).bind("userid", userId).mapToBean(UserModel.class).first();
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
            user = h.createQuery(sql).bind("username", username).mapToBean(UserModel.class).first();
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
            user = h.createQuery(sql).bind("userid", userId).mapToBean(UserModel.class).first();
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
            count = h.createQuery(sql).bind("username", username).mapTo(Integer.class).findOnly();
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
                    .bind("userid", model.getUserid())
                    .bind("username", model.getUsername())
                    .bind("password", model.getPassword())
                    .bind("alias", model.getAlias())
                    .bind("memberid", model.getMemberid())
                    .bind("isactive", false).execute();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorCreateUser " + ex);
        }
        return row;
    }
    
         public int update(String userId, UserModel user) {
        log.debug(UserDatabaseHelper.class.getName(), "- updateUser");

        final String sql = "UPDATE user SET username= :username WHERE userid= :userid;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("userid", userId)
                    .bind("username", user.getUsername()).execute();                                                          


        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorUpdateUser " + ex);
        }
        return row;
    }
         
    public int activate(String userId, boolean isActive) {
        log.debug(UserDatabaseHelper.class.getName(), "- activateUser");

        final String sql = "UPDATE user SET isactive = :isactive WHERE userid = :userid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("userid", userId)
                    .bind("isactive", isActive).execute();

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

            row = handle.createQuery(sql).bind("username", username).mapTo(Integer.class).findOnly();

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
                    .bind("username", username)
                    .bind("password", password).execute();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorChangePassword " + ex);
        }
        return row;
    }

    public int delete(String userId) {

        log.debug(UserDatabaseHelper.class.getName(), "- deleteUser");

        final String sql = "DELETE FROM user WHERE userid = :userid;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind("userid", userId).execute();

        } catch (SQLException ex) {
            log.error(UserDatabaseHelper.class.getName(), " - errorDeleteUser " + ex);
        }
        return result;
    }

}
