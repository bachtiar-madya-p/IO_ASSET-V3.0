/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.CityModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
public class CityDatabaseHelper extends BaseDatabaseHelper{

    public CityDatabaseHelper() {
        log = getLogger(this.getClass());
    }
    
    //create
    public int create(CityModel city) {
        log.debug(CityDatabaseHelper.class.getName(), "- createCity");
        final String sql = "INSERT INTO mastercity (cityid, cityname, provinceid, isactive) VALUES(:cityid, :cityname, :provinceid, :isactive);";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.CITY_CITYID, city.getCityid())
                    .bind(ConstantHelper.CITY_CITYNAME, city.getCityname())
                    .bind(ConstantHelper.PROVINCE_PROVINCEID, city.getProvinceid())
                    .bind(ConstantHelper.CITY_ISACTIVE, false).execute();
        } catch (SQLException ex) {
            log.error(CityDatabaseHelper.class.getName(), " - errorCreateCity " + ex);
        }
        return row;
    }
    
    //delete
    public int delete(String cityId) {
        log.debug(CityDatabaseHelper.class.getName(), "- deleteCity");
        final String sql = "DELETE FROM mastercity WHERE cityid = :cityid;";
        int result = 0;
        try (Handle handle = getHandle()) {
            result = handle.createUpdate(sql).bind(ConstantHelper.CITY_CITYID, cityId).execute();
        } catch (SQLException ex) {
            log.error(CityDatabaseHelper.class.getName(), " - errorDeleteCity " + ex);
        }
        return result;
    }
    
    //update
    public int update(String cityId, CityModel city) {
        log.debug(CityDatabaseHelper.class.getName(), "- updateCity");
        final String sql = "UPDATE mastercity SET  cityname= :cityname, provinceid= :provinceid WHERE cityid= :cityid;";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.CITY_CITYID, cityId)
                    .bind(ConstantHelper.CITY_CITYNAME, city.getProviincename())
                    .bind(ConstantHelper.CITY_PROVINCEID, city.getProvinceid()).execute();
        } catch (SQLException ex) {
            log.error(ProvinceDatabaseHelper.class.getName(), " - errorUpdateProvince " + ex);
        }
        return row;
    }
   
    //findById
    public CityModel findById(String cityId) {
        CityModel city = new CityModel();
        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");
        final String sql = "SELECT cityid, cityname, provinceid, IF(isactive, 'true', 'false') isactive FROM mastercity WHERE cityid = :cityid;";
        try (Handle h = getHandle()) {
            city = h.createQuery(sql).bind(ConstantHelper.CITY_CITYID, cityId).mapToBean(CityModel.class).first();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return city;
    }
    
    //list
    public List<CityModel> getList() {
        List<CityModel> cityList = new ArrayList<>();
        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListCity");
        final String sql = "SELECT cityid, cityname, provinceid, IF(isactive, 'true', 'false') isactive FROM mastercity;";
        try (Handle h = getHandle()) {
            cityList = h.createQuery(sql).mapToBean(CityModel.class).list();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListCity " + ex);
        }
        return cityList;
    }
    
    //activate
    public int activate(String cityId, boolean isActive) {
        log.debug(CityDatabaseHelper.class.getName(), "- activateCity");
        final String sql = "UPDATE mastercity SET isactive = :isactive WHERE cityid = :cityid ;";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.CITY_CITYID, cityId)
                    .bind(ConstantHelper.CITY_ISACTIVE, isActive).execute();
        } catch (SQLException ex) {
            log.error(CityDatabaseHelper.class.getName(), " - errorActivateCity " + ex);
        }
        return row;
    }
}

