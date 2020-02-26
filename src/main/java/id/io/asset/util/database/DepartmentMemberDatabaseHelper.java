/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.UserModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class DepartmentMemberDatabaseHelper extends BaseDatabaseHelper {

    public DepartmentMemberDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public List<UserModel> getList() {
        List<UserModel> list = new ArrayList<>();

        log.debug(DepartmentMemberDatabaseHelper.class.getName(), " - getListDepartmentMember");

        final String sql = "SELECT memberid, membercode, membername, email, imageaddress, description, levelid, departmentid, IF(isadmin, 'true', 'false') isadmin, IF(isactive, 'true', 'false') isactive FROM departmentmember;";

        try (Handle h = getHandle()) {
            list = h.createQuery(sql).mapToBean(UserModel.class).list();
        } catch (Exception ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorGetListDepartmentMember " + ex);
        }
        return list;
    }

    public UserModel getById(String memberId) {

        UserModel resutl = new UserModel();

        log.debug(DepartmentMemberDatabaseHelper.class.getName(), " - getDepartmentMember");

        final String sql = "SELECT memberid, membercode, membername, email, imageaddress, description, levelid, departmentid, IF(isadmin, 'true', 'false') isadmin, IF(isactive, 'true', 'false') isactive FROM departmentmember WHERE memberid = :memberid;";

        try (Handle h = getHandle()) {
            resutl = h.createQuery(sql).bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, memberId).mapToBean(UserModel.class).first();
        } catch (Exception ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorGetDepartmentMember " + ex);
        }

        return resutl;
    }

    public int create(UserModel model) {
        log.debug(DepartmentMemberDatabaseHelper.class.getName(), "- createDepartmentMember");

        final String sql = "INSERT INTO departmentmember (memberid, membercode, membername, email, imageaddress, description, levelid, departmentid, isadmin, isactive) VALUES( :memberid, :membercode, :membername, :email, :imageaddress, :description, :levelid, :departmentid, :isadmin, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, model.getMemberid())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERCODE, model.getMembercode())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERNAME, model.getMembername())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_EMAIL, model.getEmail())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_IMAGEADDRESS, model.getImageaddress())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_DESCRIPTION, model.getDescription())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_LEVELID, model.getLevelid())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_DEPARTMENTID, model.getDepartmentid())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_ISADMIN, false)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_ISACTIVE, false).execute();

        } catch (SQLException ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorCreateDepartmentMember " + ex);
        }
        return row;
    }

    public int update(String memberId, UserModel model) {
        log.debug(DepartmentMemberDatabaseHelper.class.getName(), "- updateDepartmentMember");

        final String sql = "UPDATE departmentmember SET membername= :membername, email= :email, imageaddress= :imageaddress, description= :description, levelid= :levelid, departmentid= :departmentid WHERE memberid= :memberid;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, memberId)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERNAME, model.getMembername())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_EMAIL, model.getEmail())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_IMAGEADDRESS, model.getImageaddress())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_DESCRIPTION, model.getDescription())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_LEVELID, model.getLevelid())
                    .bind(ConstantHelper.DEPARTMENTMEMBER_DEPARTMENTID, model.getDepartmentid()).execute();

        } catch (SQLException ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorUpdateDepartmentMember " + ex);
        }
        return row;
    }

    public int activate(String memberId, boolean isActive) {
        log.debug(DepartmentMemberDatabaseHelper.class.getName(), "- activateDepartmentMember");

        final String sql = "UPDATE departmentmember SET isactive = :isactive WHERE memberid = :memberid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, memberId)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_ISACTIVE, isActive).execute();

        } catch (SQLException ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorActivateDepartmentMember " + ex);
        }
        return row;
    }

    public int asAdmin(String memberId, boolean isAdmin) {
        log.debug(DepartmentMemberDatabaseHelper.class.getName(), "- asAdminDepartmentMember");

        final String sql = "UPDATE departmentmember SET isadmin = :isadmin WHERE memberid = :memberid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, memberId)
                    .bind(ConstantHelper.DEPARTMENTMEMBER_ISADMIN, isAdmin).execute();

        } catch (SQLException ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorAsAdminDepartmentMember " + ex);
        }
        return row;
    }

    public int remove(String memberId) {

        log.debug(DepartmentMemberDatabaseHelper.class.getName(), "- deleteDepartmentMember");

        final String sql = "DELETE FROM departmentmember WHERE memberid = :memberid;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind(ConstantHelper.DEPARTMENTMEMBER_MEMBERID, memberId).execute();

        } catch (SQLException ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorDeleteDepartmentMember " + ex);
        }
        return result;
    }

}
