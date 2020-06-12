package com.skcc.cloud.config.core.application.object.command;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ConfigServerApiRequestDto {

    @NotEmpty @Pattern(regexp = "twd-.*-.*",message = "Wrong Name.(twd-.*-.*)")
    private String application;

    @NotEmpty @Pattern(regexp = "local|dev|stg|alp|prd",message = "Wrong Name.(local,dev,stg,alp,prd)")
    private String profile;

    private String label;

    @NotEmpty
    private String propKey;

    @NotEmpty
    private String value;

}
