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
package id.io.asset.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

    public static final String SESSION_KEY = "session-key";

    private List<String> unprotectedList;

    public void init(FilterConfig config) throws ServletException {
        unprotectedList = new ArrayList<>();
        unprotectedList.add("/login");
        unprotectedList.add("/forgotpassword");
        unprotectedList.add("/resetpassword");
        unprotectedList.add("/validate");
    }

    public void destroy() {
    }

    private boolean isProtectedPath(String path) {
        boolean protectedPath = true;

        for (String pathUrl : unprotectedList) {
            if (path.equalsIgnoreCase(pathUrl)) {
                protectedPath = false;
                break;
            }
        }
        return protectedPath;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        boolean valid = true;

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.contains("/lib/") || path.contains("/js/") || path.contains("/css/") || path.contains("/images/")
                || path.contains("/asset/")) {
            chain.doFilter(servletRequest, servletResponse);
        } else if (path.contains("/system/")) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            if (isProtectedPath(path)) {
                HttpSession session = request.getSession(false);
                if (!(session != null && session.getAttribute(SESSION_KEY) != null)) {
                    valid = false;
                }
            }

            if (valid) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}
