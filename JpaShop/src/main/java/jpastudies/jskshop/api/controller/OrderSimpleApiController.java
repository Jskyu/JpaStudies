package jpastudies.jskshop.api.controller;

import jpastudies.jskshop.domain.Order;
import jpastudies.jskshop.service.OrderSearch;
import jpastudies.jskshop.api.dto.orderdto.OrderSimpleQueryDto;
import jpastudies.jskshop.api.dto.orderdto.SimpleOrderDto;
import jpastudies.jskshop.api.repository.OrderQueryRepository;
import jpastudies.jskshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        for(Order order : orders){
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return orders;
    }

    @GetMapping("api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());

        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(toList());

        return result;
    }
    @GetMapping("api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3(){

        List<Order> orders = orderQueryRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o))
                .collect(toList());

        return result;
    }
    @GetMapping("api/v4/simple-orders")
    public List<OrderSimpleQueryDto> orderV4(){
        List<OrderSimpleQueryDto> orderDtos = orderQueryRepository.findOrderDtos();
        return orderDtos;

    }

}
