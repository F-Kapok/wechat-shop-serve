package com.fans.vo;

import lombok.*;

import java.io.Serializable;

/**
 * className: ThemePureVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:44
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ThemePureVO implements Serializable {

    private static final long serialVersionUID = -20200531164451L;

    private Long id;
    private String title;
    private String description;
    private String name;
    private String entranceImg;
    private String extend;
    private String internalTopImg;
    private String titleImg;
    private String tplName;
    private Boolean online;

}
