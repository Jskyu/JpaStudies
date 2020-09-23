package jpastudyt4.jskshop.api.queryService;

import jpastudyt4.jskshop.api.dto.orderdto.OrderDto;
import jpastudyt4.jskshop.api.repository.OrderQueryRepository;
import jpastudyt4.jskshop.controller.OrderControllerDto;
import jpastudyt4.jskshop.domain.Order;
import jpastudyt4.jskshop.service.OrderSearch;
import jpastudyt4.jskshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

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
