package com.cherry.product.service;

import com.cherry.product.dto.DecreaseStockDTO;
import com.cherry.product.entity.ProductInfo;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午5:09
 */
public interface ProductService {
    /**
     * 查询所有在架商品
     * @param status 商品状态
     * @return 商品集合
     */
    List<ProductInfo> findUpProducts(Integer status);

    /**
     * 根据商品id集合，查询商品信息
     * @param productIds 商品id集合
     * @return 商品信息集合
     */
    List<ProductInfo> findByProductIds(List<String> productIds);

    /**
     * 减库存
     * @param decreaseStocks 减库存传输对象
     * @return 减完库存后的商品集合
     */
    List<ProductInfo> decreaseStock(List<DecreaseStockDTO> decreaseStocks);

}
