package hu.unideb.inf.Repository;

import hu.unideb.inf.Entity.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends CrudRepository <OrderItems, Long>{

}
