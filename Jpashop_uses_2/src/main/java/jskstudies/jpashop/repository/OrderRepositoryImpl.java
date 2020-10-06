package jskstudies.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jskstudies.jpashop.model.Order;
import jskstudies.jpashop.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jskstudies.jpashop.model.QDelivery.delivery;
import static jskstudies.jpashop.model.QMember.member;
import static jskstudies.jpashop.model.QOrder.order;
import static jskstudies.jpashop.model.QOrderItem.orderItem;
import static jskstudies.jpashop.model.item.QItem.item;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Order> findAll(OrderSearch orderSearch) {
        return query.select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    @Override
    public List<Order> findAllWithMemberDelivery() {
        return query
                .select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .fetch();
    }

    @Override
    public List<Order> findAllWithItem() {
        return query
                .selectDistinct(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .leftJoin(orderItem.item, item).fetchJoin()
                .fetch();
    }

    @Override
    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .offset(offset).limit(limit)
                .fetch();
    }

    private BooleanExpression nameLike(String memberName) {
        if(!StringUtils.hasText(memberName)){
            return null;
        }
        return member.name.like(memberName);
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }
        return order.status.eq(statusCond);
    }

}
