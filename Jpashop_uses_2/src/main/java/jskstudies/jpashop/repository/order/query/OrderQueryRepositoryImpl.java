package jskstudies.jpashop.repository.order.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static jskstudies.jpashop.model.QDelivery.delivery;
import static jskstudies.jpashop.model.QMember.member;
import static jskstudies.jpashop.model.QOrder.order;
import static jskstudies.jpashop.model.QOrderItem.orderItem;
import static jskstudies.jpashop.model.item.QItem.item;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders(); //Query 1번 -> N개

        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId()); //Query N번
            o.setOrderItems(orderItems);
        });
        return result;
    }

    @Override
    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        List<Long> orderIds = result.stream()
                .map(OrderQueryDto::getOrderId)
                .collect(Collectors.toList());

        List<OrderItemQueryDto> orderItems = query.select(Projections.constructor(OrderItemQueryDto.class, orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count))
                .from(orderItem)
                .join(orderItem.item, item)
                .where(orderItem.order.id.in(orderIds))
                .fetch();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    @Override
    public List<OrderFlatDto> findAllByDto_flat() {
        return query.select(Projections.constructor(OrderFlatDto.class, order.id, member.name, order.orderDate, order.status, delivery.address, item.name, orderItem.orderPrice, orderItem.count))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .join(order.orderItems, orderItem)
                .join(orderItem.item, item)
                .fetch();
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return query
                .select(Projections.constructor(OrderItemQueryDto.class, orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count))
                .from(orderItem)
                .join(orderItem.item, item)
                .where(orderItem.order.id.eq(orderId))
                .fetch();
    }

    private List<OrderQueryDto> findOrders() {
        return query
                .select(Projections.constructor(OrderQueryDto.class, order.id, member.name, order.orderDate, order.status, delivery.address))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .fetch();
    }
}
