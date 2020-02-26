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
package id.io.asset.util.rest.mapper; 

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import id.io.asset.util.rest.exception.RESTException;

@Provider
public class RESTExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException ex) {

        int status = ex.getResponse().getStatus();

        switch (status) {
            case 400:
                return Response.status(status).entity(new RESTException(Status.BAD_REQUEST))
                        .type(MediaType.APPLICATION_JSON).build();
            case 401:
                return Response.status(status).entity(new RESTException(Status.UNAUTHORIZED))
                        .type(MediaType.APPLICATION_JSON).build();
            case 500:
                return Response.status(status).entity(new RESTException(Status.INTERNAL_SERVER_ERROR))
                        .type(MediaType.APPLICATION_JSON).build();
            case 405:
                return Response.status(status).entity(new RESTException(Status.METHOD_NOT_ALLOWED))
                        .type(MediaType.APPLICATION_JSON).build();
            case 415:
                return Response.status(status).entity(new RESTException(Status.UNSUPPORTED_MEDIA_TYPE))
                        .type(MediaType.APPLICATION_JSON).build();
            default:
                return Response.status(status).entity(ex).type(MediaType.APPLICATION_JSON).build();
        }

    }

}
