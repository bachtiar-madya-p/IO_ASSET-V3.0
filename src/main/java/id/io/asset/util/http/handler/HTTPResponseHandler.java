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
package id.io.asset.util.http.handler; 

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.util.EntityUtils;
import id.io.asset.util.http.model.HTTPResponse;
import id.io.asset.util.log.BaseLogger;

public class HTTPResponseHandler implements ResponseHandler<HTTPResponse> {
    private final BaseLogger log;

    public HTTPResponseHandler() {
        super();
        log = new BaseLogger(this.getClass());
    }

    @Override
    public HTTPResponse handleResponse(HttpResponse httpResponse) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setBody(processHttpResponse(httpResponse));
        response.setCode(httpResponse.getStatusLine().getStatusCode());
        response.setHeaders(Arrays.asList(httpResponse.getAllHeaders()));

        // Close HttpResponse
        HttpClientUtils.closeQuietly(httpResponse);

        return response;
    }

    private String processHttpResponse(HttpResponse response) {
        StringBuilder builder = new StringBuilder();

        HttpEntity entity = response.getEntity();

        try (InputStream instream = entity.getContent(); Scanner sc = new Scanner(instream, "UTF-8")) {

            while (sc.hasNextLine()) {
                builder.append(sc.nextLine());
            }
            EntityUtils.consume(entity);
        } catch (Exception ex) {
            log.error("processHttpResponse", ex);
        }

        return builder.toString();
    }

}
