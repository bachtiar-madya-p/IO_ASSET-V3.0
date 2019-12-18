package id.io.asset.util.log;

import id.io.asset.util.json.JsonHelper;

public class AppLogger extends BaseLogger {

    public AppLogger(Class<?> clazz) {
        super(clazz);
    }

    // DEBUG METHODS
    public void debug(String methodName, Object obj) {
        if (allow(LogLevel.DEBUG)) {
            log.debug(() -> format(methodName, JsonHelper.toJson(obj)));
        }
    }

    public void debug(String id, String methodName, Object obj) {
        if (allow(LogLevel.DEBUG)) {
            log.debug(() -> format(id, methodName, JsonHelper.toJson(obj)));
        }
    }

    // INFO METHODS
    public void info(String methodName, Object obj) {
        if (allow(LogLevel.INFO)) {
            log.info(() -> format(methodName, JsonHelper.toJson(obj)));
        }
    }

    public void info(String id, String methodName, Object obj) {
        if (allow(LogLevel.INFO)) {
            log.info(() -> format(id, methodName, JsonHelper.toJson(obj)));
        }
    }

    // WARN METHODS
    public void warn(String methodName, Object obj) {
        if (allow(LogLevel.WARN)) {
            log.warn(() -> format(methodName, JsonHelper.toJson(obj)));
        }
    }

    public void warn(String id, String methodName, Object obj) {
        if (allow(LogLevel.WARN)) {
            log.warn(() -> format(id, methodName, JsonHelper.toJson(obj)));
        }
    }

    // ERROR METHODS
    public void error(String methodName, Object obj) {
        if (allow(LogLevel.ERROR)) {
            log.error(() -> format(methodName, JsonHelper.toJson(obj)));
        }
    }

    public void error(String id, String methodName, Object obj) {
        if (allow(LogLevel.ERROR)) {
            log.error(() -> format(id, methodName, JsonHelper.toJson(obj)));
        }
    }
}
