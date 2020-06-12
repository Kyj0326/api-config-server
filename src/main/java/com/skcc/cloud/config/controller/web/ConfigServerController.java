package com.skcc.cloud.config.controller.web;

import com.skcc.cloud.config.core.application.object.command.ConfigServerApiRequestDto;
import com.skcc.cloud.config.core.application.service.IConfigServerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/config" )
public class ConfigServerController {

    private final IConfigServerService configServerService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid ConfigServerApiRequestDto configServerApiRequestDto){
        log.debug("[Controller] ConfigServerController Called - register");
        return configServerService.register(configServerApiRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody @Valid ConfigServerApiRequestDto configServerApiRequestDto ){
        log.debug("[Controller] ConfigServerController Called - update");
        return configServerService.update(id, configServerApiRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        log.debug("[Controller] ConfigServerController Called - delete");
        configServerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getApprovalList(){
        log.debug("[Controller] ConfigServerController Called - getApprovalList");
        return configServerService.getApprovalList();
    }

    @PutMapping("/{id}/{approveFlag}")
    public ResponseEntity<Object> approveConfig(@PathVariable Integer id, @PathVariable String approveFlag){
        log.debug("[Controller] ConfigServerController Called - approveConfig");
        return configServerService.approveConfig(id, approveFlag);
    }
}
