/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.console;

import id.io.asset.manager.EncryptionManager;

/**
 *
 * @author permadi
 */
public class PasswordHash {

    public static void main(String[] args) {

        EncryptionManager.init();
        System.out.println(EncryptionManager.encrypt("iot_asset"));
        System.out.println(EncryptionManager.decrypt("UZSwzJ/QF9hUgBs49Iw+4Q=="));
        EncryptionManager.shutdown();
    }
        
        

}
