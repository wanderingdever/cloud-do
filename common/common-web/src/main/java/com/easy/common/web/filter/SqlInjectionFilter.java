package com.easy.common.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

@WebFilter("/*")
public class SqlInjectionFilter implements Filter {
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
            ".*(?:--|;|'|\"|<|>|\\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC|UNION|OR|AND)\\b).*",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (containsSqlInjection(httpRequest)) {
            throw new ServletException("SQL Injection detected");
        }
        chain.doFilter(request, response);
    }

    private boolean containsSqlInjection(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues != null) {
                for (String paramValue : paramValues) {
                    if (paramValue != null && SQL_INJECTION_PATTERN.matcher(paramValue).matches()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        // 销毁操作
    }
}