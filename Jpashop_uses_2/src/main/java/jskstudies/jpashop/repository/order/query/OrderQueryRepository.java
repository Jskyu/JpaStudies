package jskstudies.jpashop.repository.order.query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderQueryRepository {

    List<OrderQueryDto> findOrderQueryDtos();
    List<OrderQueryDto> findAllByDto_optimization();
    List<OrderFlatDto> findAllByDto_flat();
}
