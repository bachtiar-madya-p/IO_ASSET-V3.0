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
package id.io.asset.manager;

import javax.ws.rs.ApplicationPath; 
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/system")
public class ServiceManager extends ResourceConfig {

    public ServiceManager() {
        packages("id.io.asset.service", "id.io.asset.util.rest");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

}
