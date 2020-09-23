package jpastudyt4.jskshop.api.controller;

import jpastudyt4.jskshop.api.dto.orderdto.OrderDto;
import jpastudyt4.jskshop.api.dto.orderdto.OrderFlatDto;
import jpastudyt4.jskshop.api.dto.orderdto.OrderQueryDto;
import jpastudyt4.jskshop.api.queryService.OrderQueryService;
import jpastudyt4.jskshop.api.repository.OrderQueryRepository;
import jpastudyt4.jskshop.domain.Order;
import jpastudyt4.jskshop.domain.OrderItem;
import jpastudyt4.jskshop.repository.OrderRepository;
import jpastudyt4.jskshop.service.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderQueryService orderQueryService;

    @GetMapping("/api/v1/orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for(Order order : all){
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }

        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());

        return collect;

    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3(){
        return orderQueryService.orderV3();
    }

    @GetMapping("/api/v3Distinct/orders")
    public List<OrderDto> orderV3Distinct(){
       return orderQueryService.orderV3Distinct();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "100") int limit)
    {
        List<Order> allWithItem = orderQueryRepository.findAllWithMemberDelivery(offset,limit);
        return allWithItem.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> orderV4(){
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> orderV5(){
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> orderV6(){
        return orderQueryRepository.findAllByDto_flat();
    }
}
