package com.cherry.product.controller;

import com.cherry.product.dto.ProductInfoDTO;
import com.cherry.product.enums.StatusEnum;
import com.cherry.product.pojos.ResponseJson;
import com.cherry.product.pojos.ResponseJsonBuilder;
import com.cherry.product.entity.ProductCategory;
import com.cherry.product.entity.ProductInfo;
import com.cherry.product.enums.ProductEnum;
import com.cherry.product.service.CategoryService;
import com.cherry.product.service.ProductService;
import com.cherry.product.vo.ProductCategoryVO;
import com.cherry.product.vo.ProductInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                        .map(this::convert2VO).collect(Collectors.toList());
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
    public List<ProductInfoDTO> productListForOrder(@RequestBody List<String> productIds){
        log.info("/product/listForOrder:{}",productIds);

        try{
            List<ProductInfo> productInfos = productService.findByProductIds(productIds);
            return productInfos.stream().map(this::convert2DTO).collect(Collectors.toList());
        }catch (RuntimeException ex){
            log.warn(StatusEnum.SERVICE_EXCEPTION.getMsg(),ex);
            return null;
        }

    }

    /**
     * entity -> DTO
     * @param productInfo entity
     * @return DTO
     */
    private ProductInfoDTO convert2DTO(ProductInfo productInfo){
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfo, productInfoDTO);
        return productInfoDTO;
    }

    /**
     * entity -> VO
     * @param productInfo entity
     * @return VO
     */
    private ProductInfoVO convert2VO(ProductInfo productInfo){
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(productInfo,productInfoVO);
        return productInfoVO;
    }
}
