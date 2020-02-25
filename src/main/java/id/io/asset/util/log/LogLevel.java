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

public enum LogLevel {

    DEBUG(0), INFO(1), WARN(2), ERROR(3);

    private int value;

    private LogLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LogLevel getLogLevel(String level) {
        switch (level.toUpperCase()) {
            case "DEBUG":
                return DEBUG;
            case "WARN":
                return WARN;
            case "ERROR":
                return ERROR;
            case "INFO":
            default:
                return INFO;
        }
    }
}
