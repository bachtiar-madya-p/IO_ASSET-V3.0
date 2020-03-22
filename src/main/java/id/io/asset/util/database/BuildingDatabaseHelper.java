/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.BuildingModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
public class BuildingDatabaseHelper extends BaseDatabaseHelper{
    
    public BuildingDatabaseHelper(){
        log = getLogger(this.getClass());
    }
    
    //create
    public int create (BuildingModel building){
        log.debug(BuildingDatabaseHelper.class.getName(), "- createBuilding");
        final String sql = "INSERT INTO masterbuilding (buildingid, buildingname, description, cityid, isactive) VALUES (:buildingid, :buildingname, :description, :cityid, :isactive);";
        int row = 0;
        try(Handle handle = getHandle()){
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.BUILDING_BUILDINGID, building.getBuildingid())
                    .bind(ConstantHelper.BUILDING_BUILDINGNAME, building.getBuildingname())
                    .bind(ConstantHelper.BUILDING_DESCRIPTION, building.getDescription())
                    .bind(ConstantHelper.BUILDING_CITYID, building.getCityid())
                    .bind(ConstantHelper.BUILDING_ISACTIVE, false).execute();
        }catch(SQLException ex){
            log.error(BuildingDatabaseHelper.class.getName(), " - errorCcreateBuilding" + ex);
        }
        return row;
    }
    
    //delete
    public int delete(String buildingId) {
        log.debug(BuildingDatabaseHelper.class.getName(), "- deleteBuilding");
        final String sql = "DELETE FROM masterbuilding WHERE buildingid = :buildingid;";
        int result = 0;
        try (Handle handle = getHandle()) {
            result = handle.createUpdate(sql).bind(ConstantHelper.BUILDING_BUILDINGID, buildingId).execute();
        } catch (SQLException ex) {
            log.error(BuildingDatabaseHelper.class.getName(), " - errorDeleteBuilding" + ex);
        }
        return result;
    }
    
    //update
    public int update(String buildingId, BuildingModel building) {
        log.debug(BuildingDatabaseHelper.class.getName(), "- updateBuilding");
        final String sql = "UPDATE masterbuilding SET  buildingname= :buildingname, description= :description, cityid= :cityid WHERE buildingid= :buildingid;";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.BUILDING_BUILDINGID, buildingId)
                    .bind(ConstantHelper.BUILDING_BUILDINGNAME, building.getBuildingname())
                    .bind(ConstantHelper.BUILDING_DESCRIPTION, building.getDescription())
                    .bind(ConstantHelper.BUILDING_CITYID, building.getCityid()).execute();
        } catch (SQLException ex) {
            log.error(BuildingDatabaseHelper.class.getName(), " - errorUpdateBuilding " + ex);
        }
        return row;
    }
}
