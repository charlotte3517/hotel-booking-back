package com.github.charlotte3517.hotelbooking.log.filter;

import com.github.charlotte3517.hotelbooking.dao.ExternalRequestLogDao;
import com.github.charlotte3517.hotelbooking.log.model.ExternalRequestLog;
import com.github.charlotte3517.hotelbooking.log.wrapper.CustomHttpServletRequestWrapper;
import com.github.charlotte3517.hotelbooking.log.wrapper.CustomHttpServletResponseWrapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class ResponseCaptureFilter implements Filter {

    private final ExternalRequestLogDao externalRequestLogDao;

    @Autowired
    public ResponseCaptureFilter(ExternalRequestLogDao externalRequestLogDao) {
        this.externalRequestLogDao = externalRequestLogDao;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse && request instanceof HttpServletRequest) {
            CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper((HttpServletRequest) request);
            CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper((HttpServletResponse) response);

            request.setAttribute("requestWrapper", requestWrapper);
            request.setAttribute("responseWrapper", responseWrapper);

            try {
                chain.doFilter(requestWrapper, responseWrapper);
            } finally {
                if (!responseWrapper.isResponseCommitted()) {
                    responseWrapper.writeDownBody();
                    responseWrapper.flushBuffer();
                }

                Long requestId = (Long) request.getAttribute("requestId");
                logResponseData(requestId, responseWrapper);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void logResponseData(Long requestId, CustomHttpServletResponseWrapper responseWrapper) throws IOException {
        if (requestId == null) {
            return;
        }
        String responseBody = responseWrapper.getBodyStr();
        ExternalRequestLog log = new ExternalRequestLog();
        log.setRequestId(requestId);
        log.setResponseStatus(responseWrapper.getStatus());
        log.setResponseHeaders(getResponseHeaders(responseWrapper));
        log.setResponseBody(responseBody);
        log.setResponseTime(new Date());
        try {
            externalRequestLogDao.updateExternalRequestLog(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getResponseHeaders(CustomHttpServletResponseWrapper responseWrapper) {
        return responseWrapper.getResponseHeaders().toString();
    }

    @Override
    public void destroy() {

    }
}
