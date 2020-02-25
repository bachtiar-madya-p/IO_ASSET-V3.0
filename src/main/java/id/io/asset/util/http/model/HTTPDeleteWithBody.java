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
