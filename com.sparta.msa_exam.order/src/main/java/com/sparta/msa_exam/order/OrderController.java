package com.sparta.msa_exam.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문 추가
    @PostMapping
    public OrderResDto createOrder(@RequestBody OrderReqDto orderReqDto) {
        return orderService.createOrder(orderReqDto);
    }

    //주문에 상품 추가
    @PostMapping("/{orderId}")
    public OrderResDto addProductToOrder(@PathVariable Long orderId,
                                         @RequestBody Long productId) {
        return orderService.addProductToOrder(orderId, productId);
    }

    //주문 단건 조회
    @GetMapping("/{orderId}")
    public OrderResDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }


}
