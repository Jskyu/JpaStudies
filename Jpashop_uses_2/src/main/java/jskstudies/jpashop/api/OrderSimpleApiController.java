package jskstudies.jpashop.api;

import jskstudies.jpashop.model.Order;
import jskstudies.jpashop.repository.OrderRepository;
import jskstudies.jpashop.repository.OrderRepositoryImpl;
import jskstudies.jpashop.repository.OrderSearch;
import jskstudies.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jskstudies.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * xToOne ( ManyToOne, OneToOne )
 * Order
 * Order -> Member ( ManyToOne )
 * Order -> Delivery ( OneToOne )
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        for(Order order : orders){
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getId(); // Lazy 강제 초기화
        }
        return orders;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleQueryDto> orderV2(){
        //ORDER 2개
        //N + 1 -> 1 + 회원 N + 배송 N
        return orderRepository.findAll(new OrderSearch()).stream()
                .map(OrderSimpleQueryDto::new)
                .collect(toList());
    }

    /*@GetMapping("/api/v2/simple-orders")
    public Result orderV2(){
        return new Result<>(orderRepository.findAll(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(toList()));
    }*/

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> orderV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> orderV4(){
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
