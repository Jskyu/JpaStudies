package jpastudyt4.jskshop.service;

import jpastudyt4.jskshop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus status;
}
