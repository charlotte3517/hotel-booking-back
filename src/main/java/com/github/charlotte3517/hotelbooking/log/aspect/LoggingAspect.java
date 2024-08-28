package com.github.charlotte3517.hotelbooking.log.aspect;

import com.github.charlotte3517.hotelbooking.dao.ExternalRequestLogDao;
import com.github.charlotte3517.hotelbooking.log.model.ExternalRequestLog;
import com.github.charlotte3517.hotelbooking.log.wrapper.CustomHttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private ExternalRequestLogDao externalRequestLogDao;

    @Around("execution(* com.github.charlotte3517.hotelbooking.controller..*(..))")
    public Object logExternalRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        CustomHttpServletRequestWrapper requestWrapper = (CustomHttpServletRequestWrapper) request.getAttribute("requestWrapper");

        logRequestData(requestWrapper, request);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    private void logRequestData(CustomHttpServletRequestWrapper requestWrapper, HttpServletRequest request) {
        ExternalRequestLog log = new ExternalRequestLog();
        log.setRequestUrl(requestWrapper.getRequestURI());
        log.setRequestMethod(requestWrapper.getMethod());
        log.setRequestHeaders(getHeadersInfo(requestWrapper));
        log.setRequestBody(requestWrapper.getBody());
        log.setClientIp(requestWrapper.getRemoteAddr());
        log.setRequestTime(new Date());

        try {
            externalRequestLogDao.insertExternalRequestLog(log);
            request.setAttribute("requestId", log.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map.toString();
    }
}
