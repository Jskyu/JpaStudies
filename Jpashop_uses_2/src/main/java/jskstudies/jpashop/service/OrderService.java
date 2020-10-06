package jskstudies.jpashop.service;

import jskstudies.jpashop.model.Delivery;
import jskstudies.jpashop.model.Member;
import jskstudies.jpashop.model.Order;
import jskstudies.jpashop.model.OrderItem;
import jskstudies.jpashop.model.item.Item;
import jskstudies.jpashop.repository.OrderRepository;
import jskstudies.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberService.findById(memberId);
        Item item = itemService.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소, 복구
     */
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        Objects.requireNonNull(order).cancel();
    }
    @Transactional
    public void notCancel(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        Objects.requireNonNull(order).notCancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
}
