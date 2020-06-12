package com.skcc.cloud.config.core.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "properties")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConfigServerAggregate {

    @Id
    private Integer id;

    private String application;

    private String profile;

    private String label;

    private String propKey;

    private String value;

    private LocalDateTime createdDate = LocalDateTime.now();

}
