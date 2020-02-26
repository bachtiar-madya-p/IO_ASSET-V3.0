/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.DepartmentModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class DepartmentDatabaseHelper extends BaseDatabaseHelper {

    public DepartmentDatabaseHelper() {
        log = getLogger(this.getClass());
    }

    public int create(DepartmentModel department) {
        log.debug(DepartmentDatabaseHelper.class.getName(), "- createDepartment");

        final String sql = "INSERT INTO department (departmentid, departmentcode, departmentname, description, isactive) VALUES(:departmentid, :departmentcode, :departmentname, :description, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENT_DEPARTMENTID, department.getDepartmentid())
                    .bind(ConstantHelper.DEPARTMENT_DEPARTMENTCODE, department.getDepartmentcode())
                    .bind(ConstantHelper.DEPARTMENT_DEPARTMENTNAME, department.getDepartmentname())
                    .bind(ConstantHelper.DEPARTMENT_DESCRIPTION, department.getDescription())
                    .bind(ConstantHelper.DEPARTMENT_ISACTIVE, false).execute();

        } catch (SQLException ex) {
            log.error(DepartmentDatabaseHelper.class.getName(), " - errorCreateDepartment " + ex);
        }
        return row;
    }

    public int update(String departmentId, DepartmentModel department) {
        log.debug(DepartmentDatabaseHelper.class.getName(), "- updateDepartment");

        final String sql = "UPDATE department SET  departmentname= :departmentname, description= :description WHERE departmentid= :departmentid;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENT_DEPARTMENTID, departmentId)
                    .bind(ConstantHelper.DEPARTMENT_DEPARTMENTNAME, department.getDepartmentname())
                    .bind(ConstantHelper.DEPARTMENT_DESCRIPTION, department.getDescription()).execute();

        } catch (SQLException ex) {
            log.error(DepartmentDatabaseHelper.class.getName(), " - errorUpdateDepartment " + ex);
        }
        return row;
    }

    public int delete(String departmentId) {
        log.debug(DepartmentDatabaseHelper.class.getName(), "- deleteDepartment");

        final String sql = "DELETE FROM department WHERE departmentid = :departmentid;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind(ConstantHelper.DEPARTMENT_DEPARTMENTID, departmentId).execute();

        } catch (SQLException ex) {
            log.error(DepartmentDatabaseHelper.class.getName(), " - errorDeleteDepartment " + ex);
        }
        return result;
    }

    public DepartmentModel findById(String departmentId) {
        DepartmentModel department = new DepartmentModel();

        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");

        final String sql = "SELECT departmentid, departmentcode, departmentname, description, IF(isactive, 'true', 'false') isactive FROM department WHERE departmentid = :departmentid;";

        try (Handle h = getHandle()) {
            department = h.createQuery(sql).bind(ConstantHelper.DEPARTMENT_DEPARTMENTID, departmentId).mapToBean(DepartmentModel.class).first();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return department;

    }

    public List<DepartmentModel> getList() {
        List<DepartmentModel> departmentList = new ArrayList<>();

        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListDepartment");

        final String sql = "SELECT departmentid, departmentcode, departmentname, description, IF(isactive, 'true', 'false') isactive FROM department;";

        try (Handle h = getHandle()) {
            departmentList = h.createQuery(sql).mapToBean(DepartmentModel.class).list();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListDepartment " + ex);
        }
        return departmentList;
    }

    public int activate(String levelId, boolean isActive) {
        log.debug(DepartmentDatabaseHelper.class.getName(), "- activateDepartment");

        final String sql = "UPDATE department SET isactive = :isactive WHERE departmentid = :departmentid ;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.DEPARTMENT_DEPARTMENTID, levelId)
                    .bind(ConstantHelper.DEPARTMENT_ISACTIVE, isActive).execute();

        } catch (SQLException ex) {
            log.error(DepartmentDatabaseHelper.class.getName(), " - errorActivateDepartment " + ex);
        }
        return row;
    }

}
