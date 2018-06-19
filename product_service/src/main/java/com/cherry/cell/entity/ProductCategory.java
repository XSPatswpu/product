package com.cherry.cell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午5:03
 */
@Data
@Entity
public class ProductCategory {
    @Id
    private String categoryId;
    private String categoryName;
    private Integer categoryType;//类目编号
    private Date createTime;
    private Date updateTime;
}
