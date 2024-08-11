package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @CacheEvict(cacheNames = "productAllCache", allEntries = true)  //(새로운게 추가되었으니 전체 상품 캐시를 갱신)
    @CachePut(cacheNames = "productCache", key = "#result.id")  //write through - 개별 상품 캐시 갱신
    public ProductResDto createProduct(ProductReqDto productReqDto) {
        Product product = Product.createProduct(productReqDto);
        Product savedProduct = productRepository.save(product);
        return toResponseDto(savedProduct);
    }

    @Cacheable(cacheNames = "productAllCache", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<ProductResDto> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::toResponseDto);
    }

    @Cacheable(cacheNames = "productCache", key = "args[0]")
    public ProductResDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));
        return toResponseDto(product);
    }
    private ProductResDto toResponseDto(Product product) {
        return new ProductResDto(
                product.getId(),
                product.getName(),
                product.getSupply_price(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

}
