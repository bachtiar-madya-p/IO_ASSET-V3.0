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

import java.util.ArrayList; 
import java.util.List;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

public class HTTPResponse {
    private String body;
    private int code = 500;
    private List<Header> headers;
    private List<Cookie> cookies;

    public HTTPResponse() {
        headers = new ArrayList<>();
        cookies = new ArrayList<>();
    }

    public HTTPResponse(String body, int code) {
        this();
        setBody(body);
        setCode(code);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }
}
