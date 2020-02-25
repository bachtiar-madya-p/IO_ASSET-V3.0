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
package id.io.asset.util.rest.model;

import javax.ws.rs.core.Response.Status; 

public class ServiceResponse {

    private int status;
    private String description;

    public ServiceResponse() {
    }

    public ServiceResponse(Status responseStatus) {
        status = responseStatus.getStatusCode();
        description = responseStatus.getReasonPhrase();
    }

    public ServiceResponse(Status responseStatus, String description) {
        status = responseStatus.getStatusCode();
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
