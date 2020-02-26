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
package id.io.asset.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import id.io.asset.manager.ConnectionManager;
import id.io.asset.manager.EncryptionManager;
import id.io.asset.util.log.AppLogger;

@WebListener
public class ContextListener implements ServletContextListener {

    public final AppLogger log;

    public ContextListener() {
        log = new AppLogger(this.getClass());
    }

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        log.info("Application Init Started");
        EncryptionManager.init();
        ConnectionManager.init();
        log.info("Application Init Completed");
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        log.info("Application Shutdown Started");
        ConnectionManager.shutdown();
        EncryptionManager.shutdown();
        log.info("Application Shutdown Completed");
    }

}
