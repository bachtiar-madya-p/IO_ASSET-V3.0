package id.io.asset.util.helper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;

public class HttpHelper {

    public static final int INTERNAL_ERROR_CODE = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    public static final int EXTERNAL_ERROR_CODE = HttpServletResponse.SC_BAD_REQUEST;
    public static final int UNAUTHORIZED_ERROR_CODE = HttpServletResponse.SC_UNAUTHORIZED;

    public static String request(String urlText, String method, byte[] postBuffer, String... data) {
        HttpURLConnection httpURLConnection = null;
        BufferedInputStream bufferedInputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            byte[] buffer = new byte[102400];
            URL url = new URL(urlText);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            for (int i = 0; i < data.length; i = i + 2) {
                httpURLConnection.setRequestProperty(data[i], data[i + 1]);
            }
            httpURLConnection.setRequestMethod(method.toUpperCase());
            if (postBuffer != null) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(postBuffer);
            }
            httpURLConnection.connect();
            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            int read = -1;
            while ((read = bufferedInputStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, read, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return sb.toString();
    }

}
