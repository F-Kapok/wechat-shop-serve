package com.fans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * className: Paging
 *
 * @author k
 * @version 1.0
 * @description 分页工具类
 * @date 2019-04-01 10:56
 **/
@ApiModel(value = "分页结果")
@NoArgsConstructor
@Data
public class Paging<T> implements Serializable {

    private static final long serialVersionUID = -7472212864283205293L;

    @ApiModelProperty(value = "总记录数", dataType = "int")
    private Long total;
    @ApiModelProperty(value = "每页记录数", dataType = "int")
    private int count;
    @ApiModelProperty(value = "总页数", dataType = "int")
    private int totalPage;
    @ApiModelProperty(value = "当前页数", dataType = "int")
    private int page;
    @ApiModelProperty(value = "列表数据", dataType = "list")
    private List<T> items;

    public Paging(Page<T> page) {
        this.initPageParameters(page);
        this.items = page.getContent();
    }

    public void initPageParameters(Page<T> page) {
        this.total = page.getTotalElements();
        this.count = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
    }


    /**
     * 分页
     *
     * @param total 总记录数
     * @param count 每页记录数
     * @param page  当前页数
     * @param items 列表数据
     */
    public Paging(long total, int count, int page, List<T> items) {
        this.total = total;
        this.count = count;
        this.page = page;
        this.items = items;
        this.totalPage = (int) Math.ceil((double) total / count);
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
