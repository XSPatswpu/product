package com.cherry.server.service;

import com.cherry.server.entity.ProductCategory;
import com.cherry.server.entity.ProductInfo;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午5:09
 */
public interface ProductService {
    /**
     * 查询所有在架商品
     * @param status 商品状态
     * @return 商品集合
     */
    List<ProductInfo> findUpProducts(Integer status);
}
