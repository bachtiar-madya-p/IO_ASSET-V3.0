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
public class CityModel {
    
    private String cityid;
    private String cityname;
    private String provinceid;
    private boolean isactive;
    
    public CityModel (String cityid, String cityname,String provinceid, boolean isactive){
        this.cityid     = cityid;
        this.cityname   = cityname;
        this.provinceid = provinceid;
        this.isactive   = isactive;
    
    }
    
    public String getCityid(){
        return cityid;
    }
    
    public void setCityid(String cityid){
        this.cityid = cityid;
    }
    
    public String getCityname(){
        return cityname;
    }
    
    public void setCityname(String cityname){
        this.cityname = cityname;
    }
    
    public String getProvinceid(){
        return provinceid;
    }
    
    public void setProvinceid(String provinceid){
        this.provinceid = provinceid;
    }
    
    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
