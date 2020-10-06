package jskstudies.jpashop.repository.order.simplequery;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jskstudies.jpashop.model.QDelivery.delivery;
import static jskstudies.jpashop.model.QMember.member;
import static jskstudies.jpashop.model.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final JPAQueryFactory query;

    public List<OrderSimpleQueryDto> findOrderDtos(){
        return query.select(Projections.constructor(OrderSimpleQueryDto.class, order.id, order.member.name, order.orderDate, order.status, order.delivery.address))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .fetch();
    }

}
