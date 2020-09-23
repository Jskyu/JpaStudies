package jpastudyt4.jskshop.service;

import jpastudyt4.jskshop.domain.Delivery;
import jpastudyt4.jskshop.domain.Member;
import jpastudyt4.jskshop.domain.Order;
import jpastudyt4.jskshop.domain.OrderItem;
import jpastudyt4.jskshop.domain.items.Item;
import jpastudyt4.jskshop.repository.ItemRepository;
import jpastudyt4.jskshop.repository.MemberRepository;
import jpastudyt4.jskshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member findMember = memberRepository.findById(memberId).get();
        Item findItem = itemRepository.findOne(itemId);

        //배송 정보
        Delivery delivery = new Delivery();
        delivery.setAddress(findMember.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(findMember, delivery, orderItem);

        //주문 저장
        orderRepository.saveOrder(order);

        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
    @Transactional
    public void notCancel(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.notCancel();
    }


    /**
     * 검색
     */
    public List<Order> findOrder(OrderSearch orderSearch){
        return orderRepository.findAllDSL(orderSearch);
    }
}
