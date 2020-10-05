package jpastudies.jskshop.service;

import jpastudies.jskshop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus status;
}
