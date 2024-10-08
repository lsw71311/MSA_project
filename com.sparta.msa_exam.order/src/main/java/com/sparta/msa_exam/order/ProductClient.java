package com.sparta.msa_exam.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service") //상품 서비스를 호출
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResDto getProduct(@PathVariable("id") Long id);
    @GetMapping("/products")
    ProductResDto getProducts();

    @GetMapping("/products/{id}/reduceQuantity")
    void reduceProductQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);
}
