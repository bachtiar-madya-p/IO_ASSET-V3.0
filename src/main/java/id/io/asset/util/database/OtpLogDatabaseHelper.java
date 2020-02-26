/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.util.constant.ConstantHelper;
import id.io.asset.util.helper.DateHelper;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class OtpLogDatabaseHelper extends BaseDatabaseHelper {

    public OtpLogDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public int saveOtpLog(String userid, String otp) {
        log.debug(OtpLogDatabaseHelper.class.getName(), "- createOtpLog");

        final String sql = "INSERT INTO otp_log (userid, otp) VALUES( :userid, :otp);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql).bind(ConstantHelper.OTPLOG_USERID, userid).bind(ConstantHelper.OTPLOG_OTP, otp).execute();

        } catch (SQLException ex) {
            log.error(OtpLogDatabaseHelper.class.getName(), " - errorCreateOtpLog " + ex);
        }
        return row;

    }

    public boolean checkOtp(String otp) {
        boolean isValid = false;
        log.debug(OtpLogDatabaseHelper.class.getName(), "- checkOtpLog");

        final String sql = "SELECT COUNT(1) FROM otp_log WHERE otp =:otp;";

        try (Handle h = getHandle()) {

            int count = h.createQuery(sql).bind(ConstantHelper.OTPLOG_OTP, otp).mapTo(Integer.class).findOnly();

            if (count > 0) {
                isValid = true;
            }

        } catch (SQLException ex) {
            log.error(OtpLogDatabaseHelper.class.getName(), " - errorCheckOtpLog " + ex);
        }
        return isValid;
    }

    public boolean validateOtp(String userId, String otp, int expiry) {
        boolean isValid = false;
        log.debug(OtpLogDatabaseHelper.class.getName(), "- checkOtpLog");

        final String sql = "SELECT COUNT(1) FROM otp_log WHERE userid = :userid AND otp = :otp AND createdt > :createDt";

        try (Handle h = getHandle()) {
            LocalDateTime createDt = LocalDateTime.now().minusMinutes(expiry);
            int count = h.createQuery(sql)
                    .bind(ConstantHelper.OTPLOG_USERID, userId)
                    .bind(ConstantHelper.OTPLOG_OTP, otp)
                    .bind(ConstantHelper.OTPLOG_CREATEDT, DateHelper.formatDateTime(createDt)).mapTo(Integer.class).findOnly();

            if (count != 0) {
                isValid = true;
            }

        } catch (SQLException ex) {
            log.error(OtpLogDatabaseHelper.class.getName(), " - errorCheckOtpLog " + ex);
        }
        return isValid;
    }

}
