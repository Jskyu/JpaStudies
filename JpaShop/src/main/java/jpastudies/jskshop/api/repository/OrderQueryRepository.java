package jpastudies.jskshop.api.repository;

import jpastudies.jskshop.domain.Order;
import jpastudies.jskshop.api.dto.orderdto.OrderFlatDto;
import jpastudies.jskshop.api.dto.orderdto.OrderItemQueryDto;
import jpastudies.jskshop.api.dto.orderdto.OrderQueryDto;
import jpastudies.jskshop.api.dto.orderdto.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<Order> findAllWithMemberDelivery(){
        return em.createQuery("" +
                "select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order.class)
                .getResultList();
    }


    public List<OrderSimpleQueryDto> findOrderDtos(){
        return em.createQuery("select new jpastudyt4.jskshop.api.dto.orderdto.OrderSimpleQueryDto(" +
                "o.id, m.name, o.orderDate, o.orderStatus, d.address)" +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderSimpleQueryDto.class).getResultList();
    }

    public List<Order> findAllWithItem(){
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.item i", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithItemDistinct(){
        return em.createQuery(
                "select distinct o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.item i", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit){
        return em.createQuery("" +
                "select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    //V4
    public List<OrderQueryDto> findOrderQueryDtos(){
        List<OrderQueryDto> result = findOrders();

        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId){
        return em.createQuery(
                "select new jpastudyt4.jskshop.api.dto.orderdto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }


    private List<OrderQueryDto> findOrders(){
        return em.createQuery(
                "select new jpastudyt4.jskshop.api.dto.orderdto.OrderQueryDto" +
                        "(o.id, m.name, o.orderDate, o.orderStatus, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    //V5
    public List<OrderQueryDto> findAllByDto_optimization(){
        List<OrderQueryDto> result = findOrders();

        List<Long> orderIds = toOrderIds(result);
        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(orderIds);

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds){
        List<OrderItemQueryDto> orderItems = em.createQuery(
                "select new jpastudyt4.jskshop.api.dto.orderdto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto ::getOrderId));
    }

    private List<Long> toOrderIds(List<OrderQueryDto> result){
        return result.stream()
                    .map(o -> o.getOrderId())
                    .collect(Collectors.toList());
    }

    //V6
    public List<OrderFlatDto> findAllByDto_flat(){
        return em.createQuery(
                "select distinct new jpastudyt4.jskshop.api.dto.orderdto.OrderFlatDto" +
                        "(o.id, m.name, o.orderDate, o.orderStatus, d.address, i.name, oi.orderPrice, oi.count)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d" +
                        " join o.orderItems oi" +
                        " join oi.item i", OrderFlatDto.class)
                .getResultList();
    }
}
