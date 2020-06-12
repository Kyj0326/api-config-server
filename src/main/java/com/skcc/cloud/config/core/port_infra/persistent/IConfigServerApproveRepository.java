package com.skcc.cloud.config.core.port_infra.persistent;

import com.skcc.cloud.config.core.domain.entity.ConfigServerApproveAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IConfigServerApproveRepository extends JpaRepository<ConfigServerApproveAggregate,Integer> {
    List<ConfigServerApproveAggregate> findByApproveFlag(String approveFlag);
}
