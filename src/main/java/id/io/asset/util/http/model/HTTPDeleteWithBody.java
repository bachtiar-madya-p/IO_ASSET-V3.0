package id.io.asset.util.http.model;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HTTPDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HTTPDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HTTPDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    public HTTPDeleteWithBody() {
        super();
    }
}
