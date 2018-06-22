package com.cherry.product.repository;

import com.cherry.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午5:28
 */
public interface ProductRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findByProductIdIn(List<String> productIds);
}
