package com.cherry.product.service;

import com.cherry.product.entity.ProductCategory;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午8:38
 */
public interface CategoryService {
    /**
     * 根据类目编号集合查询所有的类目集合
     * @param categoryTypeList 类目编号集合
     * @return 类目集合
     */
    List<ProductCategory> findByCategoryTypeList(List<Integer> categoryTypeList);
}
