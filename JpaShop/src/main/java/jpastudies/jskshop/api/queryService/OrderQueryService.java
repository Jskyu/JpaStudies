package jpastudies.jskshop.api.queryService;

import jpastudies.jskshop.service.OrderSearch;
import jpastudies.jskshop.service.OrderService;
import jpastudies.jskshop.api.dto.orderdto.OrderDto;
import jpastudies.jskshop.api.repository.OrderQueryRepository;
import jpastudies.jskshop.controller.OrderControllerDto;
import jpastudies.jskshop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderQueryRepository orderQueryRepository;
    private final OrderService orderService;

    public List<OrderDto> orderV3(){
        List<Order> allWithItem = orderQueryRepository.findAllWithItem();
        return allWithItem.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    public List<OrderDto> orderV3Distinct(){
        List<Order> allWithItem = orderQueryRepository.findAllWithItemDistinct();
        return allWithItem.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    public List<OrderControllerDto> list(OrderSearch orderSearch){
        List<Order> orders = orderService.findOrder(orderSearch);
        List<OrderControllerDto> result = orders.stream()
                .map(order -> new OrderControllerDto(order.getId(), order.getOrderItems().get(0).getItem().getName(),
                        order.getMember().getName(), order.getOrderItems().get(0).getOrderPrice(),
                        order.getOrderItems().get(0).getCount(), order.getOrderDate(), order.getOrderStatus()))
                .collect(Collectors.toList());
        return result;
    }
}
