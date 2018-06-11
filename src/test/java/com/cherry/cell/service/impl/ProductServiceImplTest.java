package com.cherry.cell.service.impl;

import com.cherry.cell.AppTest;
import com.cherry.cell.entity.ProductInfo;
import com.cherry.cell.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午5:34
 */
public class ProductServiceImplTest extends AppTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpProducts() {
        List<ProductInfo> products = productService.findUpProducts(0);
        System.out.println(products);
    }
}