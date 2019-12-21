/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.manager.EncryptionManager;
import id.io.asset.service.model.UserLevelModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class UserLevelDatabaseHelper extends BaseDatabaseHelper {

    private UUIDGeneratorHelper uuidGenerator;

    public UserLevelDatabaseHelper() {
        log = getLogger(this.getClass());
        uuidGenerator = new UUIDGeneratorHelper();
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

    public int addConfig(UserLevelModel model) {
        log.debug(UserLevelDatabaseHelper.class.getName(), "- createConfiguration");

        final String sql = "INSERT INTO configuration (`key`, value) VALUES (:key, :value);";
        int row = 0;
        try (Handle handle = getHandle()) {
//            String encryptedPassword;
//            if (ConstantHelper.EMAIL_PASSWORD.equals(key)) {
//                EncryptionManager.init();
//                encryptedPassword = EncryptionManager.encrypt(value);
//                row = handle.createUpdate(sql).bind("key", key).bind("value", encryptedPassword).execute();
//            } else {
//                row = handle.createUpdate(sql).bind("key", key).bind("value", value).execute();
//            }
        } catch (SQLException ex) {
            log.error(UserLevelDatabaseHelper.class.getName(), " - errorCreateConfiguration " + ex);
        }
        return row;
    }

    public int updateConfig(String key, String value) {
        return 0;
    }

}
