package startApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.entities.Order;
import startApp.entities.User;
import startApp.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findByUserId(long id) {
        return orderRepository.findByUserId(id);
    }

    public void deleteByUser(User user){
        orderRepository.deleteByUser(user);
    }
}
