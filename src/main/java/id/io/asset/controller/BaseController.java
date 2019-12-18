/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.controller;

import java.sql.SQLException;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.jdbi.v3.core.statement.Update;
import id.io.asset.manager.ConnectionManager;
import id.io.asset.manager.PropertyManager;
import id.io.asset.util.log.AppLogger;

/**
 *
 * @author permadi
 */
public class BaseController {

    protected AppLogger log;

    protected AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    protected Handle getHandle() throws SQLException {
        return ConnectionManager.getHandle();
    }

    protected Handle getHandle(RowMapper<?>... rowMappers) throws SQLException {
        Handle h = getHandle();

        if (rowMappers != null && rowMappers.length > 0) {
            for (RowMapper<?> mapper : rowMappers) {
                h.registerRowMapper(mapper);
            }
        }
        return h;
    }

    protected boolean executeUpdate(Update update) {
        return update.execute() > 0;
    }

    protected int executeAndGetId(Update update) {
        return update.executeAndReturnGeneratedKeys().mapTo(Integer.class).one();
    }

    protected boolean executeBatch(PreparedBatch batch) {
        int[] resultArr = batch.execute();

        for (int result : resultArr) {
            if (result < 0) {
                return false;
            }
        }
        return true;
    }

    protected void start(String methodName) {
        log.debug(methodName, "start");
    }

    protected void completed(String methodName) {
        log.debug(methodName, "completed");
    }

    protected String getProperty(String key) {
        return PropertyManager.getInstance().getProperty(key);
    }

    protected boolean getBoolProperty(String key) {
        return PropertyManager.getInstance().getBoolProperty(key);
    }
}
