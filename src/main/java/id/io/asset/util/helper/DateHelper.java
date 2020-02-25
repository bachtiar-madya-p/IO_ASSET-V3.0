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
package id.io.asset.util.helper; 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private static final DateTimeFormatter FILE_DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter JSON_DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateHelper() {}

    public static String formatFileDateTime(LocalDateTime dt) {
        if (dt != null) {
            return FILE_DT_FORMAT.format(dt);
        }
        return null;

    }

    public static String formatDateTime(LocalDateTime dt) {
        if (dt != null) {
            return dt.format(JSON_DT_FORMAT);
        }
        return null;
    }

    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.parse(str, JSON_DT_FORMAT);
    }
}
