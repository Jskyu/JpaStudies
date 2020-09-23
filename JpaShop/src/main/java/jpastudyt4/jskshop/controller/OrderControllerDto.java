package jpastudyt4.jskshop.controller;

import jpastudyt4.jskshop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

/*

#	회원명	대표상품 이름	대표상품 주문가격	대표상품 주문수량	상태	일시
 */
@Data
public class OrderControllerDto {

    private Long id;
    private String itemName;
    private String memberName;
    private int orderPrice;
    private int count;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public OrderControllerDto(Long id, String itemName, String memberName, int orderPrice, int count, LocalDateTime orderDate, OrderStatus status){
        this.id = id;
        this.itemName = itemName;
        this.memberName = memberName;
        this.orderPrice = orderPrice;
        this.count = count;
        this.orderDate = orderDate;
        this.status = status;
    }
}
