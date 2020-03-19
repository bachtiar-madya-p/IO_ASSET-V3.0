/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.ProvinceModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
public class ProvinceDatabaseHelper extends BaseDatabaseHelper {
    
    public ProvinceDatabaseHelper() {
        log = getLogger(this.getClass());
    }
    
    //create
    public int create(ProvinceModel province) {
        log.debug(ProvinceDatabaseHelper.class.getName(), "- createProvince");

        final String sql = "INSERT INTO masterprovince (provinceid, provincecode, provincename, isactive) VALUES(:provinceid, :provincecode, :provincename, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.PROVINCE_PROVINCEID, province.getProvinceid())
                    .bind(ConstantHelper.PROVINCE_PROVINCECODE, province.getProvincecode())
                    .bind(ConstantHelper.PROVINCE_PROVINCENAME, province.getProviincename())
                    .bind(ConstantHelper.DEPARTMENT_ISACTIVE, false).execute();

        } catch (SQLException ex) {
            log.error(ProvinceDatabaseHelper.class.getName(), " - errorCreateProvince " + ex);
        }
        return row;
    }
    
    //update
    public int update(String provinceId, ProvinceModel province) {
        log.debug(ProvinceDatabaseHelper.class.getName(), "- updateProvince");

        final String sql = "UPDATE masterprovince SET  provincename= :provincename WHERE provinceid= :provinceid;";
        int row = 0;
        try (Handle handle = getHandle()) {

            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.PROVINCE_PROVINCEID, provinceId)
                    .bind(ConstantHelper.PROVINCE_PROVINCENAME, province.getProviincename()).execute();

        } catch (SQLException ex) {
            log.error(ProvinceDatabaseHelper.class.getName(), " - errorUpdateProvince " + ex);
        }
        return row;
    }
    
    //delete
    public int delete(String provinceId) {
        log.debug(ProvinceDatabaseHelper.class.getName(), "- deleteProvince");

        final String sql = "DELETE FROM masterprovince WHERE provinceid = :provinceid;";
        int result = 0;
        try (Handle handle = getHandle()) {

            result = handle.createUpdate(sql).bind(ConstantHelper.PROVINCE_PROVINCEID, provinceId).execute();

        } catch (SQLException ex) {
            log.error(ProvinceDatabaseHelper.class.getName(), " - errorDeleteProvince " + ex);
        }
        return result;
    }
}
