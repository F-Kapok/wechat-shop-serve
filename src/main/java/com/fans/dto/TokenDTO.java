package com.fans.dto;

import lombok.*;

import java.io.Serializable;

/**
 * className: TokenDTO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-01 23:13
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -20200601231321L;

    private String token;

}
