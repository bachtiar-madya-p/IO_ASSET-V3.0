/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
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
