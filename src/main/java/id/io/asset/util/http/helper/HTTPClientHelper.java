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
package id.io.asset.util.http.helper; 

import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import id.io.asset.util.http.model.HTTPHeader;
import id.io.asset.util.http.model.HTTPParameter;
import id.io.asset.util.http.model.HTTPRequest;

public class HTTPClientHelper {

    private HTTPClientHelper() {}

    public static void populateHeader(HttpRequest request, HTTPHeader headers) {
        if (headers != null && !headers.isEmpty()) {
            List<Header> list = headers.entrySet().stream()
                    .map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).collect(Collectors.toList());
            list.forEach(request::addHeader);
        }
    }

    public static List<NameValuePair> getNVPList(HTTPParameter params) {
        return params.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static CookieStore getCookieStore(final HTTPRequest request) {
        CookieStore cookieStore = new BasicCookieStore();

        if (request.hasCookies()) {
            request.getCookies().forEach(cookieStore::addCookie);
        }

        return cookieStore;
    }

    public static boolean validateHttpParameter(HTTPParameter params) {
        return params != null && !params.isEmpty();
    }
}
