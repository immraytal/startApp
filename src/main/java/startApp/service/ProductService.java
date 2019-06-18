package startApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import startApp.entities.Product;
import startApp.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(Product.class);

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        try {
            productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return;
        }
        logger.info("Product " + product.getId() + " has been saved in DB");
    }

    public Product getById(long id) {
        return productRepository.getById(id);
    }

    public Product getByPrice(double price){
        return productRepository.getByPrice(price);
    }

    public void deleteById(long id) {
       try {
            productRepository.deleteById(id);
       } catch (Exception e){
           e.printStackTrace();
           logger.error(e.getMessage());
           return;
       }
        logger.info("Product " + id + " has been deleted");
    }
}
