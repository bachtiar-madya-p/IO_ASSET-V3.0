/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import java.time.LocalDateTime;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class AuthenticationController extends BaseController {

    public AuthenticationController() {
        log = getLogger(this.getClass());
    }

    public boolean authenticate(String username, String password) {
        boolean isAuth = false;

        final String methodName = "create";
        start(methodName);
        final String sql = "SELECT COUNT(1) FROM MasterUser WHERE username = :username AND password = md5(:password)";

        try (Handle h = getHandle()) {
            int count = h.createQuery(sql).bind("username", username).bind("password", password).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isAuth = true;
            }
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isAuth;
    }

    public String getRole(String username) {
        String output = null;

        final String methodName = "create";
        start(methodName);
        final String sql = "SELECT role FROM MasterUser WHERE username = ':username' ";

        try (Handle h = getHandle()) {
//
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);

        return output;
    }

}
