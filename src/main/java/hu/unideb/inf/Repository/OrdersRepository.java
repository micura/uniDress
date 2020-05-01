package hu.unideb.inf.Repository;

import hu.unideb.inf.Entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    @Query(value = "SELECT * FROM ORDERS u WHERE u.customer_customer_id = ?1", nativeQuery = true)
    List<Orders> findOrdersByUserId(long id);
}
