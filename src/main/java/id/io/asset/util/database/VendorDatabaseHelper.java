/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.util.database;

import id.io.asset.model.VendorModel;
import id.io.asset.util.constant.ConstantHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author user
 */
public class VendorDatabaseHelper extends BaseDatabaseHelper {
    
    public VendorDatabaseHelper(){
        log = getLogger(this.getClass());
    }
    //create
    public int create(VendorModel vendor){
        log.debug(VendorDatabaseHelper.class.getName(),"- createVendor");
        
        final String sql = "INSERT INTO mastervendor (vendorid, vendorcode, vendorname, contact, email, address, note, rate) VALUES (:vendorid, :vendorcode, :vendorname, :contact, :email, :address, :note, :rate);";
        int row = 0;
        try(Handle handle = getHandle()){
            row  = handle.createUpdate(sql)
                    .bind(ConstantHelper.VENDOR_VENDORID, vendor.getVendorid())
                    .bind(ConstantHelper.VENDOR_VENDORCODE, vendor.getVendorcode())
                    .bind(ConstantHelper.VENDOR_VENDORNAME, vendor.getVendorname())
                    .bind(ConstantHelper.VENDOR_CONTACT, vendor.getContact())
                    .bind(ConstantHelper.VENDOR_EMAIL, vendor.getEmail())
                    .bind(ConstantHelper.VENDOR_ADDRESS, vendor.getAddress())
                    .bind(ConstantHelper.VENDOR_NOTE, vendor.getNote())
                    .bind(ConstantHelper.VENDOR_RATE, vendor.getRate()).execute();
            
        }catch(SQLException ex){
            log.error(VendorDatabaseHelper.class.getName(), "- errorCreateVendor" + ex);
        }
        return row;
    }
    
    //vendorCodeValidity
    public boolean vendorCodeValidity(String vendorCode){
        log.debug(VendorDatabaseHelper.class.getName()," - vendorCodeValidity");
        boolean isValid = false;
        final String sql = "SELECT count(1) FROM mastervendor WHERE vendorcode= :vendorcode";
        int row = 0;
        try(Handle handle = getHandle()){
            row = handle.createQuery(sql).bind(ConstantHelper.VENDOR_VENDORCODE, vendorCode).mapTo(Integer.class).findOnly();
            if(row == 0){
                isValid = true;
            }
        }catch(SQLException ex){
            log.error(VendorDatabaseHelper.class.getName()," - errorVendorCodeValidity" + ex);
        }
        return isValid;
    }
    
    //update
    public int update(String vendorId, VendorModel vendor){
        log.debug(VendorDatabaseHelper.class.getName(),"- updateVendor");
        
        final String sql = "UPDATE mastervendor SET vendorcode= :vendorcode, vendorname= :vendorname, contact= :contact, email= :email, address= :address, note= :note, rate= :rate WHERE vendorid= :vendorid;";
        int row = 0;
        try(Handle handle = getHandle()){
            row = handle.createUpdate(sql)
                    .bind(ConstantHelper.VENDOR_VENDORID, vendorId)
                    .bind(ConstantHelper.VENDOR_VENDORCODE, vendor.getVendorcode())
                    .bind(ConstantHelper.VENDOR_VENDORNAME, vendor.getVendorname())
                    .bind(ConstantHelper.VENDOR_CONTACT, vendor.getContact())
                    .bind(ConstantHelper.VENDOR_EMAIL, vendor.getEmail())
                    .bind(ConstantHelper.VENDOR_ADDRESS, vendor.getAddress())
                    .bind(ConstantHelper.VENDOR_NOTE, vendor.getNote())
                    .bind(ConstantHelper.VENDOR_RATE, vendor.getRate()).execute();
        }catch(SQLException ex){
            log.error(VendorDatabaseHelper.class.getName(),"- errorUpdateVendor" + ex);
        }
        return row;
    }
    
    //delete 
    public int delete(String vendorId){
        log.debug(VendorDatabaseHelper.class.getName(),"- deleteVendor");
        
        final String sql = "DELETE FROM mastervendor WHERE vendorid = :vendorid;";
        int result = 0;
        try(Handle handle = getHandle()){
            result = handle.createUpdate(sql).bind(ConstantHelper.VENDOR_VENDORID, vendorId).execute();
                    
        }catch(SQLException ex){
            log.error(VendorDatabaseHelper.class.getName()," - errorDeleteVendor " + ex);
        }
        return result;
    }
    //findById
    public VendorModel findById(String vendorId){
        VendorModel vendor = new VendorModel();
        log.debug(MemberLevelDatabaseHelper.class.getName()," - getListUserLevel");
        
        final String sql = "SELECT vendorid, vendorcode, vendorname, contact, email, address, note, rate, registered FROM mastervendor WHERE vendorid= :vendorid;";
        try(Handle handle = getHandle()){
            vendor = handle.createQuery(sql).bind(ConstantHelper.VENDOR_VENDORID, vendorId).mapToBean(VendorModel.class).first();
        }catch(SQLException ex){
            log.error(MemberLevelDatabaseHelper.class.getName()," - errorGetListUserLevel" + ex);
        }
        return vendor;
    }
    //list
    public List<VendorModel> getList(){
        List<VendorModel> vendorList = new ArrayList<>();
        log.debug(MemberLevelDatabaseHelper.class.getName()," - getListVendor");
        
        final String sql = "SELECT vendorid, vendorcode, vendorname, contact, email, address, note, rate, registered FROM mastervendor;";
        try(Handle handle = getHandle()){
            vendorList = handle.createQuery(sql).mapToBean(VendorModel.class).list();
        }catch(SQLException ex){
            log.error(MemberLevelDatabaseHelper.class.getName()," - errorGetListVendor"+ ex);
        }
        return vendorList;
    }
}
