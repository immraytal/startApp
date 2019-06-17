package startApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
