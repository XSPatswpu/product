package com.cherry.server.controller;

import com.cherry.common.enums.StatusEnum;
import com.cherry.common.pojos.ResponseJson;
import com.cherry.common.pojos.ResponseJsonBuilder;
import com.cherry.server.entity.ProductCategory;
import com.cherry.server.entity.ProductInfo;
import com.cherry.server.enums.ProductEnum;
import com.cherry.server.service.CategoryService;
import com.cherry.server.service.ProductService;
import com.cherry.server.vo.ProductCategoryVO;
import com.cherry.server.vo.ProductInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        List<ProductCategoryVO> categoryVOs = new ArrayList<>();
        try {
            //1.查询所有在架的商品
            List<ProductInfo> productInfos = productService.findUpProducts(ProductEnum.UP.getCode());
            //2.根据查询出来的商品获取所有的类目
            List<Integer> categoryTypeList = productInfos.stream()
                    .map(ProductInfo::getCategoryType)
                    .collect(Collectors.toList());
            List<ProductCategory> categories = categoryService.findByCategoryTypeList(categoryTypeList);


            for (ProductCategory category : categories) {
                ProductCategoryVO categoryVO = new ProductCategoryVO();
                categoryVO.setCategoryType(category.getCategoryType());
                categoryVO.setCategoryName(category.getCategoryName());

                List<ProductInfoVO> productVOs = productInfos.stream()
                        .filter(e -> e.getCategoryType() == categoryVO.getCategoryType())
                        .map(this::convert).collect(Collectors.toList());
                categoryVO.setProductInfoVOList(productVOs);

                categoryVOs.add(categoryVO);

            }
        }catch(RuntimeException ex){
            log.warn(ex.getMessage(),ex);
            return ResponseJsonBuilder.createFailOfSystem(ex.getMessage());
        }
        return ResponseJsonBuilder.createSuccess(categoryVOs);
    }

    /**
     * 根据商品id集合查询商品信息（给订单服务调用）
     * @param productIds 商品id集合
     */
    @PostMapping("/listForOrder")
    public ResponseJson productListForOrder(@RequestBody List<String> productIds){
        log.info("/product/listForOrder:{}",productIds);

        try{
            List<ProductInfo> productInfos = productService.findByProductIds(productIds);
            List<ProductInfoVO> productInfoVOs = productInfos.stream().map(this::convert).collect(Collectors.toList());
            return ResponseJsonBuilder.createSuccess(productInfoVOs);
        }catch (RuntimeException ex){
            log.warn(StatusEnum.SERVICE_EXCEPTION.getMsg(),ex);
            return ResponseJsonBuilder.createFailOfService(StatusEnum.SERVICE_EXCEPTION.getMsg());
        }

    }

    private ProductInfoVO convert(ProductInfo productInfo){
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(productInfo, productInfoVO);
        return productInfoVO;
    }
}
