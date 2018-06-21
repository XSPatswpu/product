package com.cherry.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午4:38
 */
@Data
@Entity
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;//商品单价
    private Integer productStock;//商品库存
    private String productDescription;
    private String productIcon;
    private Integer productStatus;//商品状态 0-正常，1-下架
    private Integer categoryType;//商品类目编号
    private Date createTime;
    private Date updateTime;
}
