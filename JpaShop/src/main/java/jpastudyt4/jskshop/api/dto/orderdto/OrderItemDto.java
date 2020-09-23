package jpastudyt4.jskshop.api.dto.orderdto;

import jpastudyt4.jskshop.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem){
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
