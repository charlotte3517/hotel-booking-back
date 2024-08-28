package com.github.charlotte3517.hotelbooking.log.config;

import com.github.charlotte3517.hotelbooking.dao.ExternalRequestLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.charlotte3517.hotelbooking.log.filter.ResponseCaptureFilter;

@Configuration
public class FilterConfig {

    @Autowired
    private ExternalRequestLogDao externalRequestLogDao;

    @Bean
    public FilterRegistrationBean<ResponseCaptureFilter> loggingFilter() {
        FilterRegistrationBean<ResponseCaptureFilter> registrationBean = new FilterRegistrationBean<>();
        ResponseCaptureFilter filter = new ResponseCaptureFilter(externalRequestLogDao);
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
