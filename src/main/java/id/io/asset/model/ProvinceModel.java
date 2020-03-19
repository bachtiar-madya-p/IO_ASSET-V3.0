/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.model;

/**
 *
 * @author user
 */
public class ProvinceModel {
    private String provinceid;
    private String provincecode;
    private String provincename;
    private boolean isactive;
    
    public ProvinceModel (String provinceid, String provincecode,String provincename, boolean isactive){
        this.provinceid     = provinceid;
        this.provincecode   = provincecode;
        this.provincename   = provincename;
        this.isactive       = isactive;
    
    }
    
    public String getProvinceid(){
        return provinceid;
    }
    
    public void setProvinceid(String provinceid){
        this.provinceid = provinceid;
    }
    
    public String getProvincecode(){
        return provincecode;
    }
    
    public void setProvincecode(String provincecode){
        this.provincecode = provincecode;
    }
    
    public String getProviincename(){
        return provincename;
    }
    
    public void setProvincename(String provincename){
        this.provincename = provincename;
    }
    
    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
