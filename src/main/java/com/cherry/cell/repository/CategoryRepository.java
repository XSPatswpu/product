package com.cherry.cell.repository;

import com.cherry.cell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午8:28
 */
public interface CategoryRepository extends JpaRepository<ProductCategory,String> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
