package jpastudyt4.jskshop.api.dto.orderdto;

import jpastudyt4.jskshop.domain.Address;
import jpastudyt4.jskshop.domain.Order;
import jpastudyt4.jskshop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order){
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getOrderStatus();
        address = order.getDelivery().getAddress();
    }
}
