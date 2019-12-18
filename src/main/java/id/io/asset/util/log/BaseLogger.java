package id.io.asset.util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseLogger {
    protected final Logger log;
    protected final LogLevel level;

    public BaseLogger(Class<?> clazz) {
        log = LogManager.getLogger(clazz);
        level = getLogLevel();
    }

    // DEBUG METHODS
    public void debug(String text) {
        if (allow(LogLevel.DEBUG)) {
            log.debug(() -> text);
        }
    }

    public void debug(String methodName, String text) {
        if (allow(LogLevel.DEBUG)) {
            log.debug(() -> format(methodName, text));
        }
    }

    public void debug(String id, String methodName, String text) {
        if (allow(LogLevel.DEBUG)) {
            log.debug(() -> format(id, methodName, text));
        }
    }

    // INFO METHODS
    public void info(String text) {
        if (allow(LogLevel.INFO)) {
            log.info(() -> text);
        }
    }

    public void info(String methodName, String text) {
        if (allow(LogLevel.INFO)) {
            log.info(() -> format(methodName, text));
        }
    }

    public void info(String id, String methodName, String text) {
        if (allow(LogLevel.INFO)) {
            log.debug(() -> format(id, methodName, text));
        }
    }

    // WARN METHODS
    public void warn(String text) {
        if (allow(LogLevel.WARN)) {
            log.warn(() -> text);
        }
    }

    public void warn(String methodName, String text) {
        if (allow(LogLevel.WARN)) {
            log.warn(() -> format(methodName, text));
        }
    }

    public void warn(String id, String methodName, String text) {
        if (allow(LogLevel.WARN)) {
            log.debug(() -> format(id, methodName, text));
        }
    }

    // ERROR METHODS
    public void error(String methodName, String text) {
        if (allow(LogLevel.ERROR)) {
            log.error(() -> format(methodName, text));
        }
    }

    public void error(String methodName, Exception ex) {
        if (allow(LogLevel.ERROR)) {
            log.error(() -> methodName, ex);
        }
    }

    public void error(String methodName, String text, Exception ex) {
        if (allow(LogLevel.ERROR)) {
            log.error(() -> format(methodName, text), ex);
        }
    }

    public void error(String id, String methodName, String text, Exception ex) {
        if (allow(LogLevel.ERROR)) {
            log.error(() -> format(id, methodName, text), ex);
        }
    }

    protected String format(String id, String methodName, String text) {
        return "[" + id + "] [" + methodName + "] " + text;
    }

    protected String format(String methodName, String text) {
        return "[" + methodName + "] " + text;
    }

    protected boolean allow(LogLevel msgLevel) {
        return msgLevel.getValue() >= level.getValue();
    }

    protected LogLevel getLogLevel() {
        return LogLevel.DEBUG;
    }
}
