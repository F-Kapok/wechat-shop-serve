package com.fans.entity;

import com.alibaba.fastjson.JSONObject;
import com.fans.utils.JsonUtils;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * className: User
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 23:31
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Where(clause = "delete_time is null")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200531233153L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String openid;
    private String nickname;
    private Long unifyUid;
    private String email;
    private String password;
    private String mobile;
    private String wxProfile;

    public JSONObject getWxProfile() {
        return JsonUtils.string2Obj(wxProfile, JSONObject.class);
    }

    public void setWxProfile(JSONObject wxProfile) {
        this.wxProfile = JsonUtils.obj2String(wxProfile );
    }
}