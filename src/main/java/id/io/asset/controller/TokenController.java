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
package id.io.asset.controller;

import id.io.asset.util.helper.DateHelper;
import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

public class TokenController extends BaseController {

    public TokenController() {
        log = getLogger(this.getClass());
    }

    public boolean save(String email, String token) {
       final String methodName = "save";
        start(methodName);
        final String sql = "INSERT INTO token (email,token) VALUES (:email,:token);";

        boolean result = false;

        try (Handle h = getHandle()) {
            Update update = h.createUpdate(sql).bind("email", email).bind("token", token);
            result = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return result;
    }
public boolean checkToken(String token) {
        final String methodName = "checkToken";
        start(methodName);
        final String sql
                = "SELECT COUNT(1) FROM token WHERE token =:token;";

        boolean result = false;

        try (Handle h = getHandle()) {

            int count = h.createQuery(sql).bind("token", token).mapTo(Integer.class).findOnly();

            if (count > 0) {
                result = true;
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return result;

    }

    public boolean validate(String email, String token, int expiry) {
        final String methodName = "validate";
        start(methodName);
        final String sql
                = "SELECT COUNT(1) FROM token WHERE email = :email AND token =:token AND create_dt > :createDt;";

        boolean result = false;

        try (Handle h = getHandle()) {
            LocalDateTime createDt = LocalDateTime.now().minusMinutes(expiry);
            int count = h.createQuery(sql).bind("email", email).bind("token", token)
                    .bind("createDt", DateHelper.formatDateTime(createDt)).mapTo(Integer.class).findOnly();

            if (count > 0) {
                result = true;
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return result;

    }

    public String generateToken(String email) {
        String result = UUID.randomUUID().toString();
        result = DigestUtils.sha256Hex(email + result);
        return result;
    }

}
