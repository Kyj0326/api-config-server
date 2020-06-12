package com.skcc.cloud.config.core.port_infra.persistent;

import com.skcc.cloud.config.core.domain.entity.ConfigServerAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConfigServerRepository extends JpaRepository<ConfigServerAggregate,Integer> {
}
