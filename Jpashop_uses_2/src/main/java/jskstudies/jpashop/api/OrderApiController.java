package jskstudies.jpashop.api;

import jskstudies.jpashop.model.Order;
import jskstudies.jpashop.repository.OrderRepository;
import jskstudies.jpashop.repository.order.query.OrderQueryDto;
import jskstudies.jpashop.repository.order.query.OrderQueryRepository;
import jskstudies.jpashop.service.query.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderQueryService orderQueryService;

    @GetMapping("api/v1/orders")
    public List<Order> ordersV1() {
        return orderQueryService.ordersV1();
    }

    @GetMapping("api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderQueryService.ordersV2();
    }

    @GetMapping("api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderQueryService.ordersV3();
    }


    @GetMapping("api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "offset", defaultValue = "100") int limit) {
        return orderQueryService.ordersV3_1(offset, limit);
    }

    @GetMapping("api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryService.ordersV4();
    }

    @GetMapping("api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryService.ordersV5();
    }

    @GetMapping("api/v6/orders")
    public List<OrderQueryDto> ordersV6_1() {
        return orderQueryService.ordersV6_1();
    }


}
