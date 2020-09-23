package jpastudyt4.jskshop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpastudyt4.jskshop.domain.Order;
import jpastudyt4.jskshop.domain.OrderStatus;
import jpastudyt4.jskshop.domain.QMember;
import jpastudyt4.jskshop.domain.QOrder;
import jpastudyt4.jskshop.service.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static jpastudyt4.jskshop.domain.QMember.member;
import static jpastudyt4.jskshop.domain.QOrder.order;

@Repository
public class OrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    OrderRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void saveOrder(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
        String jpql = "select o from Order o join o.member m";
                /*+ " where o.orderStatus = :orderStatus" +
                " and m.name = :name";*/
        boolean isFirstCondition = true;

        if(orderSearch.getStatus() != null){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else
                jpql += " and";
            jpql += " o.orderStatus = :orderStatus";
        }

        if(StringUtils.hasText(orderSearch.getMemberName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else
                jpql += " and";
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(15);

        if(orderSearch.getStatus() != null){
            query = query.setParameter("orderStatus", orderSearch.getStatus());
        }
        if(StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

    public List<Order> findAllDSL(OrderSearch orderSearch){

        return query.select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getStatus()), nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression nameLike(String memberName){
        if(!StringUtils.hasText(memberName)){
            return null;
        }
        return member.name.like(memberName);
    }

    private BooleanExpression statusEq(OrderStatus statusCond){
        if(statusCond == null){
            return null;
        }
        return order.orderStatus.eq(statusCond);
    }

}
