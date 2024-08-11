package com.sparta.msa_exam.product;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Value("${server.port}")
    private int serverPort;

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public ProductResDto createProduct(@RequestBody ProductReqDto productReqDto) {
        return productService.createProduct(productReqDto);
    }

    @GetMapping
    public Page<ProductResDto> getProducts(Pageable pageable, HttpServletResponse response) {
        response.setHeader("Server-Port", String.valueOf(serverPort));
        return productService.getProducts(pageable);
    }

}
