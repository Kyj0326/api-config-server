package com.skcc.cloud.config.core.application.service;

import com.skcc.cloud.config.core.application.object.command.ConfigServerApiRequestDto;
import org.springframework.http.ResponseEntity;

public interface IConfigServerService {

    ResponseEntity<Object> register(ConfigServerApiRequestDto configServerApiRequestDto);

    ResponseEntity<Object> update(Integer id, ConfigServerApiRequestDto configServerApiRequestDto);

    void delete(Integer id);

    ResponseEntity<Object> getApprovalList();

    ResponseEntity<Object> approveConfig(Integer id, String approveFlag);

    void postMonitor();
}
