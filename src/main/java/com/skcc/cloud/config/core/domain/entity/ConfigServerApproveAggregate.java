package com.skcc.cloud.config.core.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "properties_tmp")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConfigServerApproveAggregate {

    @Id
    @GeneratedValue
    private Integer id;

    private String application;

    private String profile;

    private String label;

    private String propKey;

    private String value;

    private String approveFlag;

    private LocalDateTime createdDate = LocalDateTime.now();

}
