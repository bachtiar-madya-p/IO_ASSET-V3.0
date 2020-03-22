/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.BuildingModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                    .bind(ConstantHelper.CITY_CITYID, building.getCityid())
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
    
    //findById
    public BuildingModel findById(String buildingId) {
        BuildingModel building = new BuildingModel();
        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListUserLevel");
        final String sql = "SELECT buildingid, buildingname, description, cityid, IF(isactive, 'true', 'false') isactive FROM masterbuilding WHERE buildingid = :buildingid;";
        try (Handle h = getHandle()) {
            building = h.createQuery(sql).bind(ConstantHelper.BUILDING_BUILDINGID, buildingId).mapToBean(BuildingModel.class).first();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListUserLevel " + ex);
        }
        return building;
    }
    
    //list
    public List<BuildingModel> getList() {
        List<BuildingModel> buildingList = new ArrayList<>();
        log.debug(MemberLevelDatabaseHelper.class.getName(), " - getListBuilding");
        final String sql = "SELECT buildingid, buildingname, description, cityid, IF(isactive, 'true', 'false') isactive FROM masterbuilding;";
        try (Handle h = getHandle()) {
            buildingList = h.createQuery(sql).mapToBean(BuildingModel.class).list();
        } catch (Exception ex) {
            log.error(MemberLevelDatabaseHelper.class.getName(), " - errorGetListBuilding " + ex);
        }
        return buildingList;
    }
    
    //activate
    public int activate(String buildingId, boolean isActive) {
        log.debug(BuildingDatabaseHelper.class.getName(), "- activateBuilding");
        final String sql = "UPDATE masterbuilding SET isactive = :isactive WHERE buildingid = :buildingid ;";
        int row = 0;
        try (Handle handle = getHandle()) {
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.BUILDING_BUILDINGID, buildingId)
                    .bind(ConstantHelper.BUILDING_ISACTIVE, isActive).execute();
        } catch (SQLException ex) {
            log.error(BuildingDatabaseHelper.class.getName(), " - errorActivateBuilding " + ex);
        }
        return row;
    }
}
