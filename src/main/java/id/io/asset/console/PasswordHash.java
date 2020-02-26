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
package id.io.asset.console;

import id.io.asset.manager.EncryptionManager; 

public class PasswordHash {

    public static void main(String[] args) {

        EncryptionManager.init();
        System.out.println(EncryptionManager.encrypt("V1m@n@l0gy123!"));
        System.out.println(EncryptionManager.decrypt("PGca9WaveULlmpQ8AjOHhQ=="));
        EncryptionManager.shutdown();
    }
        
        

}
