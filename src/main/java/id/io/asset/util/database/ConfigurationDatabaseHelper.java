/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.manager.EncryptionManager;
import id.io.asset.util.configuration.Configuration;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;
import org.json.JSONObject;

/**
 *
 * @author permadi
 */
public class ConfigurationDatabaseHelper extends BaseDatabaseHelper {

    public ConfigurationDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public ConcurrentHashMap<String, String> getAllConfiguration() {

        ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();

        log.debug(ConfigurationDatabaseHelper.class.getName(), " - getListConfiguration");
        List<Configuration> configList = new ArrayList<>();
        final String sql = "SELECT " + ConstantHelper.CONFIG_DB_COL_KEY + ", " + ConstantHelper.CONFIG_DB_COL_VALUE + " FROM " + ConstantHelper.CONFIG_DB_TABLE + ";";

        try (Handle h = getHandle()) {
            configList = h.createQuery(sql).mapToBean(Configuration.class).list();
            for (Configuration configuration : configList) {

                String key = configuration.getKey();
                String value = configuration.getValue();
                result.put(key, value);
            }

        } catch (Exception ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorGetConfiguration " + ex);
        }

        return result;

    }

    public int updateConfig(String key, String value) {

        log.debug(ConfigurationDatabaseHelper.class.getName(), "- updateConfiguration");

        final String sql = "UPDATE configuration SET value= :value WHERE `key`=:key;";
        int row = 0;
        try (Handle handle = getHandle()) {
            String encryptedPassword;
            if (ConstantHelper.EMAIL_PASSWORD.equals(key)) {
                encryptedPassword = EncryptionManager.encrypt(value);
                row = handle.createUpdate(sql).bind("key", key).bind("value", encryptedPassword).execute();
            } else {
                row = handle.createUpdate(sql).bind("key", key).bind("value", value).execute();
            }
        } catch (SQLException ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorUpdateConfiguration " + ex);
        }
        return row;
    }

    public int addConfig(String key, String value) {

        log.debug(ConfigurationDatabaseHelper.class.getName(), "- createConfiguration");

        final String sql = "INSERT INTO configuration (`key`, value) VALUES (:key, :value);";
        int row = 0;
        try (Handle handle = getHandle()) {
            String encryptedPassword;
            if (key.contains("PASSWORD")) {
                EncryptionManager.init();
                encryptedPassword = EncryptionManager.encrypt(value);
                row = handle.createUpdate(sql).bind("key", key).bind("value", encryptedPassword).execute();
            } else {
                row = handle.createUpdate(sql).bind("key", key).bind("value", value).execute();
            }
        } catch (SQLException ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorCreateConfiguration " + ex);
        }
        return row;
    }

    public int removeConfig(String key) {

        log.debug(ConfigurationDatabaseHelper.class.getName(), "- deleteConfiguration");

        final String sql = "DELETE FROM configuration WHERE `key` = :key;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind("key", key).execute();

        } catch (SQLException ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorDeleteConfiguration " + ex);
        }
        return result;
    }

    public JSONObject getOtpEmailConfiguration() {
        log.debug(ConfigurationDatabaseHelper.class.getName(), " - getOtpEmailConfiguration");

        JSONObject mailConfig = new JSONObject();

        List<Configuration> configList = new ArrayList<>();
        final String sql = "SELECT " + ConstantHelper.CONFIG_DB_COL_KEY + ", " + ConstantHelper.CONFIG_DB_COL_VALUE + " FROM " + ConstantHelper.CONFIG_DB_TABLE + " WHERE " + ConstantHelper.CONFIG_DB_COL_KEY + " LIKE 'OTP_%';";

        try (Handle h = getHandle()) {
            configList = h.createQuery(sql).mapToBean(Configuration.class).list();
            for (Configuration configuration : configList) {
                mailConfig.put(configuration.getKey(), configuration.getValue());
            }

        } catch (Exception ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorGetOtpEmailConfiguration " + ex);
        }
        return mailConfig;
    }

    public JSONObject getEmailConfiguration() {
        log.debug(ConfigurationDatabaseHelper.class.getName(), " - getOtpEmailConfiguration");

        JSONObject mailConfig = new JSONObject();

        List<Configuration> configList = new ArrayList<>();
        final String sql = "SELECT " + ConstantHelper.CONFIG_DB_COL_KEY + ", " + ConstantHelper.CONFIG_DB_COL_VALUE + " FROM " + ConstantHelper.CONFIG_DB_TABLE + " WHERE " + ConstantHelper.CONFIG_DB_COL_KEY + " LIKE 'EMAIL_%';";

        try (Handle h = getHandle()) {
            configList = h.createQuery(sql).mapToBean(Configuration.class).list();
            for (Configuration configuration : configList) {
                mailConfig.put(configuration.getKey(), configuration.getValue());
            }

        } catch (Exception ex) {
            log.error(ConfigurationDatabaseHelper.class.getName(), " - errorGetOtpEmailConfiguration " + ex);
        }
        return mailConfig;
    }

}
