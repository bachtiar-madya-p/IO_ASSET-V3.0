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
    
}

