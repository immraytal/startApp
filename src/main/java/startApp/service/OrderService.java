package startApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.entities.Order;
import startApp.entities.User;
import startApp.repository.OrderRepository;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
        logger.info("Order " + order.getId() + " has been saved in DB");
    }

    public Order findByUserId(long id) {
        return orderRepository.findByUserId(id);
    }

    public void deleteByUser(User user){
        orderRepository.deleteByUser(user);
        logger.info("Order has been deleted");
    }
}
