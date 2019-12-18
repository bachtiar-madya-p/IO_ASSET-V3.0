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
