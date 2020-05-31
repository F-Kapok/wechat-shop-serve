package com.fans.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * className: Spec
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 13:35
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Spec implements Serializable {

    private static final long serialVersionUID = -20200531133559L;

    @JsonProperty("key_id")
    private Long keyId;
    private String key;
    @JsonProperty("value_id")
    private Long valueId;
    private String value;

}
