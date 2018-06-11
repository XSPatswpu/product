package com.cherry.cell.controller;

import com.cherry.cell.entity.ProductCategory;
import com.cherry.cell.entity.ProductInfo;
import com.cherry.cell.pojos.ResponseJson;
import com.cherry.cell.pojos.ResponseJsonBuilder;
import com.cherry.cell.service.CategoryService;
import com.cherry.cell.service.ProductService;
import com.cherry.cell.vo.ProductCategoryVO;
import com.cherry.cell.vo.ProductInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午2:57
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 1.查询所有在架的商品
     * 2.根据查询出来的商品获取所有的类目
     * 3.查询所有的类目信息
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResponseJson productList(){
        log.info("/product/list");
        //1.查询所有在架的商品
        List<ProductInfo> productInfos = productService.findUpProducts(0);
        //2.根据查询出来的商品获取所有的类目
        List<Integer> categoryTypeList = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> categories = categoryService.findByCategoryTypeList(categoryTypeList);

        List<ProductCategoryVO> categoryVOs = new ArrayList<>();
        for (ProductCategory category : categories) {
            ProductCategoryVO categoryVO = new ProductCategoryVO();
            categoryVO.setCategoryType(category.getCategoryType());
            categoryVO.setCategoryName(category.getCategoryName());

            List<ProductInfoVO> productVOs = productInfos.stream()
                    .filter(e -> e.getCategoryType() == categoryVO.getCategoryType())
                    .map(e -> {
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(e,productInfoVO);
                        return productInfoVO;
                    })
                    .collect(Collectors.toList());
            categoryVO.setProductInfoVOList(productVOs);

            categoryVOs.add(categoryVO);

        }

        return ResponseJsonBuilder.createSuccess(categoryVOs);
    }
}
