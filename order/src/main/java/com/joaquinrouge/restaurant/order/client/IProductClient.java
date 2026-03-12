package com.joaquinrouge.restaurant.order.client;

import com.joaquinrouge.restaurant.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface IProductClient {

    @GetMapping("/products/{id}")
    ProductDto findProductById(@PathVariable Long id);

}
