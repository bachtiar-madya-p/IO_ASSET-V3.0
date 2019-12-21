/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.service.model.UserLevelModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class UserLevelDatabaseHelper extends BaseDatabaseHelper {

    public UserLevelDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public List<UserLevelModel> levelList() {
        List<UserLevelModel> userLevelList = new ArrayList<>();

        log.debug(UserLevelDatabaseHelper.class.getName(), " - getListUserLevel");

        final String sql = "SELECT levelid, levelcode, levelname, description, IF(isactive, 'true', 'false') isactive FROM user_level;";

        try (Handle h = getHandle()) {
            userLevelList = h.createQuery(sql).mapToBean(UserLevelModel.class).list();
        } catch (Exception ex) {
            log.error(UserLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return userLevelList;
    }

    public UserLevelModel getLevelById(String levelId) {

        UserLevelModel userLevel = new UserLevelModel();

        log.debug(UserLevelDatabaseHelper.class.getName(), " - getListUserLevel");

        final String sql = "SELECT levelid, levelcode, levelname, description, IF(isactive, 'true', 'false') isactive FROM user_level WHERE levelid = :levelId;";

        try (Handle h = getHandle()) {
            userLevel = h.createQuery(sql).bind("levelId", levelId).mapToBean(UserLevelModel.class).first();
        } catch (Exception ex) {
            log.error(UserLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }

        return userLevel;
    }

    public int create(UserLevelModel model) {
        log.debug(UserLevelDatabaseHelper.class.getName(), "- createMemberLevel");

        final String sql = "INSERT INTO user_level (levelid, levelcode, levelname, description, isactive) VALUES( :levelid, :levelcode, :levelname, :description, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("levelid", model.getLevelid())
                    .bind("levelcode", model.getLevelcode())
                    .bind("levelname", model.getLevelname())
                    .bind("description", model.getDescription())
                    .bind("isactive", false).execute();

        } catch (SQLException ex) {
            log.error(UserLevelDatabaseHelper.class.getName(), " - errorCreateMemberLevel " + ex);
        }
        return row;
    }

    public int update(String levelId, UserLevelModel model) {
        log.debug(UserLevelDatabaseHelper.class.getName(), "- updateMemberLevel");

        final String sql = "UPDATE user_level SET levelname = :levelname, description = :description WHERE levelid = :levelid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("levelid", levelId)
                    .bind("levelname", model.getLevelname())
                    .bind("description", model.getDescription()).execute();

        } catch (SQLException ex) {
            log.error(UserLevelDatabaseHelper.class.getName(), " - errorUpdateMemberLevel " + ex);
        }
        return row;
    }

    public int activate(String levelId, boolean isActive) {
        log.debug(UserLevelDatabaseHelper.class.getName(), "- activateMemberLevel");

        final String sql = "UPDATE user_level SET isactive = :isactive WHERE levelid = :levelid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind("levelid", levelId)
                    .bind("isactive", isActive).execute();

        } catch (SQLException ex) {
            log.error(UserLevelDatabaseHelper.class.getName(), " - errorActivateMemberLevel " + ex);
        }
        return row;
    }

    public int remove(String levelId) {

        log.debug(ConfiguratinDatabaseHelper.class.getName(), "- deleteMemberLevel");

        final String sql = "DELETE FROM user_level WHERE levelId = :levelId;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind("levelId", levelId).execute();

        } catch (SQLException ex) {
            log.error(ConfiguratinDatabaseHelper.class.getName(), " - errorDeleteMemberLevel " + ex);
        }
        return result;
    }

}
