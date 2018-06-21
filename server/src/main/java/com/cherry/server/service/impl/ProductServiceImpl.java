package com.cherry.server.service.impl;

import com.cherry.server.entity.ProductInfo;
import com.cherry.server.repository.ProductRepository;
import com.cherry.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午5:27
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductInfo> findUpProducts(Integer status) {
        return productRepository.findByProductStatus(status);
    }

    @Override
    public List<ProductInfo> findByProductIds(List<String> productIds) {
        return productRepository.findByProductIdIn(productIds);
    }

}
