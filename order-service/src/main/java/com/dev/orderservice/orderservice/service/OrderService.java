package com.dev.orderservice.orderservice.service;

import com.dev.orderservice.orderservice.dto.OrderListItemsDto;
import com.dev.orderservice.orderservice.dto.OrderRequest;
import com.dev.orderservice.orderservice.model.Order;
import com.dev.orderservice.orderservice.model.OrderListItems;
import com.dev.orderservice.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderListItems> orderListItems = orderRequest.getOrderListItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderListItemsList(orderListItems);

        orderRepository.save(order);
    }

    private OrderListItems mapToDto(OrderListItemsDto orderListItemsDto) {
        OrderListItems orderListItems = new OrderListItems();
        orderListItems.setPrice(orderListItemsDto.getPrice());
        orderListItems.setQuantity(orderListItemsDto.getQuantity());
        orderListItems.setSkuCode(orderListItemsDto.getSkuCode());
        return orderListItems;
    }
}
