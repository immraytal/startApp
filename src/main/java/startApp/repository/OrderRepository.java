package startApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import startApp.entities.Order;
import startApp.entities.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    public Order findByUserId(long id);
    public void deleteByUser(User user);
}
