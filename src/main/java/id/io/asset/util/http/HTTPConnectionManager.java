package id.io.asset.util.http;

import javax.net.ssl.SSLContext;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import id.io.asset.util.log.BaseLogger;

public class HTTPConnectionManager {
    private static PoolingHttpClientConnectionManager connManager;

    private static final BaseLogger log = new BaseLogger(HTTPConnectionManager.class);

    private HTTPConnectionManager() {}

    private static void init() {
        connManager = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", trustSelfSignCert()).build());
        connManager.setMaxTotal(200);
        connManager.setDefaultMaxPerRoute(20);
    }

    public static PoolingHttpClientConnectionManager getInstance() {
        if (connManager == null) {
            init();
        }
        return connManager;
    }

    private static SSLConnectionSocketFactory trustSelfSignCert() {
        SSLConnectionSocketFactory sslConnFactory = null;
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
            sslConnFactory = new SSLConnectionSocketFactory(sslContext, new String[] {"TLSv1.2"}, null,
                    NoopHostnameVerifier.INSTANCE);
        } catch (Exception ex) {
            log.error("trustSelfSignCert", ex);
        }
        return sslConnFactory;
    }
}
