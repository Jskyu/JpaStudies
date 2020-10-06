package jskstudies.jpashop.repository;

import jskstudies.jpashop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryCustom {

    List<Order> findAll(OrderSearch orderSearch);
    List<Order> findAllWithMemberDelivery();
    List<Order> findAllWithItem();
    List<Order> findAllWithMemberDelivery(int offset, int limit);
}
