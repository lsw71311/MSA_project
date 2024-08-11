package com.sparta.msa_exam.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    // OrderProduct 객체를 생성하는 메소드
    private List<OrderProduct> createOrderProducts(List<Long> productIds, Order order) {
        return productIds.stream()
                .map(productId -> new OrderProduct(null, order, productId))
                .collect(Collectors.toList());
    }
    @Transactional
    public OrderResDto createOrder(OrderReqDto requestDto) {
        Order order = new Order();
        order.setName(requestDto.getName());

        List<OrderProduct> orderProducts = createOrderProducts(requestDto.getProductIds(), order);
        order.setProduct_ids(orderProducts);

        Order savedOrder = orderRepository.save(order);
        return toResponseDto(savedOrder);
    }

    private OrderResDto toResponseDto(Order order) {
        // OrderProduct 리스트에서 product_id만 추출하여 List<Long>으로 변환
        List<Long> productIds = order.getProduct_ids().stream()
                .map(OrderProduct::getProductId)
                .collect(Collectors.toList());

        return new OrderResDto(
                order.getName(),
                productIds,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    @Transactional
    public OrderResDto addProductToOrder(Long orderId, Long productId) {

        ProductResDto product = productClient.getProduct(productId);
        if(product == null) {
            throw new IllegalArgumentException("해당 상품이 존재하지 않습니다. id=" + productId);
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. id=" + orderId));

        OrderProduct orderProduct = new OrderProduct(null, order, productId);
        order.getProduct_ids().add(orderProduct);

        Order addedOrder = orderRepository.save(order);

        return toResponseDto(addedOrder);

    }

    public OrderResDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. id=" + orderId));

        return toResponseDto(order);
    }
}
