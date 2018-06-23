package com.cherry.product.service.impl;

import com.cherry.product.dto.DecreaseStockDTO;
import com.cherry.product.entity.ProductInfo;
import com.cherry.product.enums.ResultEnum;
import com.cherry.product.exception.ProductException;
import com.cherry.product.repository.ProductRepository;
import com.cherry.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> decreaseStock(List<DecreaseStockDTO> decreaseStocks) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockDTO decreaseStockDTO : decreaseStocks) {
            //1.查询库存
            Optional<ProductInfo> productOptional = productRepository.findById(decreaseStockDTO.getProductId());

            if(!productOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productOptional.get();

            //2.减库存（库存不够抛异常）
            Integer resultStock = productInfo.getProductStock() - decreaseStockDTO.getDecreaseStock();
            if(resultStock < 0){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            productInfo.setProductStock(resultStock);

            productRepository.save(productInfo);
            productInfoList.add(productInfo);
        }

        return productInfoList;
    }

}
