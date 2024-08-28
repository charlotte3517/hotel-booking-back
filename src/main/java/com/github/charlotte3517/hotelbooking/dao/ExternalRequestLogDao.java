package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.log.model.ExternalRequestLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalRequestLogDao {

    List<ExternalRequestLog> getAllExternalRequestLogs();

    ExternalRequestLog getExternalRequestLogById(@Param("requestId") Integer requestId);

    void insertExternalRequestLog(@Param("log") ExternalRequestLog externalRequestLog);

    void updateExternalRequestLog(@Param("log") ExternalRequestLog externalRequestLog);

    void deleteExternalRequestLog(@Param("requestId") Integer requestId);
}
