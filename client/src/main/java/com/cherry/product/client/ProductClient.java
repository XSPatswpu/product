package com.cherry.product.client;

import com.cherry.product.dto.ProductInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/21 下午5:53
 */
@FeignClient(name = "product")
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfoDTO> findProduct(@RequestBody  List<String> productIds);
}
