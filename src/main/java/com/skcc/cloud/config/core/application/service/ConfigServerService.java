package com.skcc.cloud.config.core.application.service;

import com.skcc.cloud.config.core.application.object.command.ConfigServerApiRequestDto;
import com.skcc.cloud.config.core.domain.entity.ConfigServerAggregate;
import com.skcc.cloud.config.core.domain.entity.ConfigServerApproveAggregate;
import com.skcc.cloud.config.core.port_infra.persistent.IConfigServerApproveRepository;
import com.skcc.cloud.config.core.port_infra.persistent.IConfigServerRepository;
import com.skcc.common.error.ErrorCode;
import com.skcc.common.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfigServerService implements IConfigServerService {

    private final IConfigServerRepository configServerRepository;
    private final IConfigServerApproveRepository configServerApproveRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity register(ConfigServerApiRequestDto configServerApiRequestDto) {

            log.debug("[Service] ConfigServerService Called - register [{}]", configServerApiRequestDto.toString());
            ConfigServerApproveAggregate configServerApproveAggregate = modelMapper.map(configServerApiRequestDto, ConfigServerApproveAggregate.class);
            configServerApproveAggregate.setApproveFlag("N");
            return ResponseEntity.ok(configServerApproveRepository.save(configServerApproveAggregate));
    }

    @Override
    public ResponseEntity<Object> update(Integer id, ConfigServerApiRequestDto configServerApiRequestDto) {

            log.debug("[Service] ConfigServerService Called - update [{}, {}]", id, configServerApiRequestDto.toString());
            ConfigServerApproveAggregate configServerApproveAggregate = modelMapper.map(configServerApiRequestDto, ConfigServerApproveAggregate.class);
            configServerRepository.findById(id).orElseThrow(() -> new BusinessException("NotFound", ErrorCode.ENTITY_NOT_FOUND));
            configServerApproveAggregate.setId(id);
            configServerApproveAggregate.setApproveFlag("N");
            return ResponseEntity.ok(configServerApproveRepository.save(configServerApproveAggregate));

    }

    @Override
    public void delete(Integer id) {
        log.debug("[Service] ConfigServerService Called - delete [{}]", id);
        configServerRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> getApprovalList() {
        return ResponseEntity.ok(configServerApproveRepository.findByApproveFlag("N"));
    }

    @Override
    public ResponseEntity<Object> approveConfig(Integer id, String approveFlag) {
        log.info("[Service] ConfigServerService Called - approveConfig [{}, {}]", id, approveFlag);
        ConfigServerApproveAggregate configServerApproveAggregate = configServerApproveRepository.findById(id)
                                                                    .orElseThrow(() -> new BusinessException("NotFound", ErrorCode.ENTITY_NOT_FOUND));


        if ( approveFlag.equals("Y") || approveFlag.equals("y") ){
            ConfigServerAggregate configServerAggregate = ConfigServerAggregate.builder().id(configServerApproveAggregate.getId())
                                                                                        .application(configServerApproveAggregate.getApplication())
                                                                                        .profile(configServerApproveAggregate.getProfile())
                                                                                        .label(configServerApproveAggregate.getLabel())
                                                                                        .propKey(configServerApproveAggregate.getPropKey())
                                                                                        .value(configServerApproveAggregate.getValue())
                                                                                        .createdDate(LocalDateTime.now())
                                                                                        .build();
            configServerApproveAggregate.setApproveFlag("Y");
            configServerApproveRepository.save(configServerApproveAggregate);
            postMonitor();
            return ResponseEntity.ok(configServerRepository.save(configServerAggregate));

        } else{
            configServerApproveAggregate.setApproveFlag("X");
            return ResponseEntity.ok(configServerApproveRepository.save(configServerApproveAggregate));
        }
    }

	@Override
	public void postMonitor() {
        log.info("[Service] ConfigServerService Called - postMonitor");
		URL url = null;
		URLConnection urlConnection = null;
		String sUrl = "http://ad1c80b24b9174b798cfb45644fc1335-2098827307.ap-northeast-2.elb.amazonaws.com:8888/monitor";
		
		try {
			url = new URL(sUrl);
			urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
		} catch(Exception e) {
            e.printStackTrace();
        }
	}
}
