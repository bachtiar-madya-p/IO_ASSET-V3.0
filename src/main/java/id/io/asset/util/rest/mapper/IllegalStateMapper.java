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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status; 
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider; 
import id.io.asset.util.rest.model.ServiceResponse;

@Provider
public class IllegalStateMapper implements ExceptionMapper<IllegalStateException> {

    @Override
    public Response toResponse(IllegalStateException exception) {
        return Response.status(Status.NOT_ACCEPTABLE).entity(new ServiceResponse(Status.NOT_ACCEPTABLE)).build();
    }
}