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
