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

import id.io.asset.model.UserModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

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
            resutl = h.createQuery(sql).bind("memberid", memberId).mapToBean(UserModel.class).first();
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
                    .bind("memberid", model.getMemberid())
                    .bind("membercode", model.getMembercode())
                    .bind("membername", model.getMembername())
                    .bind("email", model.getEmail())
                    .bind("imageaddress", model.getImageaddress())
                    .bind("description", model.getDescription())
                    .bind("levelid", model.getLevelid())
                    .bind("departmentid", model.getDepartmentid())
                    .bind("isadmin", false)
                    .bind("isactive", false).execute();

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
                    .bind("memberid", memberId)
                    .bind("membername", model.getMembername())
                    .bind("email", model.getEmail())
                    .bind("imageaddress", model.getImageaddress())
                    .bind("description", model.getDescription())
                    .bind("levelid", model.getLevelid())
                    .bind("departmentid", model.getDepartmentid()).execute();

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
                    .bind("memberid", memberId)
                    .bind("isactive", isActive).execute();

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
                    .bind("memberid", memberId)
                    .bind("isadmin", isAdmin).execute();

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

            result = handle.createUpdate(sql).bind("memberid", memberId).execute();

        } catch (SQLException ex) {
            log.error(DepartmentMemberDatabaseHelper.class.getName(), " - errorDeleteDepartmentMember " + ex);
        }
        return result;
    }

}
