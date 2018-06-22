package com.cherry.product.service.impl;

import com.cherry.product.entity.ProductCategory;
import com.cherry.product.repository.CategoryRepository;
import com.cherry.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午8:41
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeList(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
