package jskstudies.jpashop.api;

import jskstudies.jpashop.model.Address;
import jskstudies.jpashop.model.Order;
import jskstudies.jpashop.model.OrderStatus;
import jskstudies.jpashop.service.query.OrderQueryService;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order o) {
        orderId = o.getId();
        name = o.getMember().getName();
        orderDate = o.getOrderDate();
        orderStatus = o.getStatus();
        address = o.getDelivery().getAddress();
        orderItems = o.getOrderItems().stream()
                .map(OrderItemDto::new).collect(toList());
    }
}