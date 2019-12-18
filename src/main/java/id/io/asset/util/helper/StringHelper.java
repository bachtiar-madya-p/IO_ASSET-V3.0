package id.io.asset.util.helper;

public class StringHelper {

    private StringHelper() {}

    public static boolean validate(String text) {
        return text != null && text.trim().length() > 0;
    }
}
