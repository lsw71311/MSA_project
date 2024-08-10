package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResDto createProduct(@RequestBody ProductReqDto productReqDto) {
        return productService.createProduct(productReqDto);
    }

    @GetMapping
    public Page<ProductResDto> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }


}
