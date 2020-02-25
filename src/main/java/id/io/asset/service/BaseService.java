/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2019 Identiticoders, and individual contributors as indicated by the @author tags. All Rights Reserved
 *
 * The contents of this file are subject to the terms of the Common Development and Distribution License (the License).
 *
 * Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not
 * allowed.
 *
 */
package id.io.asset.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import id.io.asset.manager.PropertyManager;
import id.io.asset.util.log.AppLogger;
import id.io.asset.util.rest.model.ServiceResponse;

public class BaseService {
 
    protected AppLogger log;

    @Context
    protected HttpServletRequest request;

    protected Response getErrorResponse() {
        return buildResponse(Status.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    protected Response getResourceNotFoundResponse() {
        return buildResponse(Status.NOT_FOUND, "Resource Not Found");
    }

    protected Response getAcceptedResponse(String message) {
        return buildResponse(Status.ACCEPTED, message);
    }

    protected Response getSuccessResponse() {
        return buildResponse(Status.OK, "Success");
    }

    protected Response getSuccessResponse(Object obj) {
        return Response.status(Status.OK).entity(obj).build();
    }

    protected Response getInvalidRequestResponse() {
        return buildResponse(Status.BAD_REQUEST, "Invalid Request");
    }

    protected Response getConflictedResponse(String message) {
        return buildResponse(Status.CONFLICT, message);
    }

    private Response buildResponse(Status status, String message) {
        return Response.status(status).entity(new ServiceResponse(status, message)).build();
    }

    protected AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    protected Executor initThreadPool() {
        return Executors.newFixedThreadPool(30);
    }

    protected void start(String methodName) {
        log.debug(methodName, "start");
    }

    protected void completed(String methodName) {
        log.debug(methodName, "completed");
    }

    protected String getProperty(String key) {
        return PropertyManager.getInstance().getProperty(key);
    }


}
