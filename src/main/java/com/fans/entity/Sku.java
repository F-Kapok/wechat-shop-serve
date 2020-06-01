package com.fans.entity;

import com.fans.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

/**
 * className: Sku
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 19:46
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null and online = 1")
public class Sku extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200530194639L;

    @Id
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    @Column(name = "spu_id")
    private Long spuId;
    private String specs;
    private String code;
    private Long stock;
    private Long categoryId;
    private Long rootCategoryId;

    public List<Spec> getSpecs() {
        return JsonUtils.string2Obj(this.specs, new TypeReference<List<Spec>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = JsonUtils.obj2String(specs);
    }
}
