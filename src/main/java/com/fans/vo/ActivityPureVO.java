package com.fans.vo;

import com.fans.entity.Activity;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * className: ActivituPureVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:47
 **/
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ActivityPureVO implements Serializable {

    private static final long serialVersionUID = -20200602224712L;

    private Long id;
    private String title;
    private String entranceImg;
    private Boolean online;
    private String remark;
    private Date startTime;
    private Date endTime;

    public ActivityPureVO(Activity activity) {
        BeanUtils.copyProperties(activity, this);
    }
}
