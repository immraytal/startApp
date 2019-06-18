package startApp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import startApp.entities.Product;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    static long id;

    @Test
    public void saveProduct() {
        Product product = new Product(1L, "name", 123.12, "hhtp", false);
        productService.saveProduct(product);
        Product target = productService.getByPrice(123.12);
        id = target.getId();
        Assert.assertEquals(product.getImg(), productService.getByPrice(123.12).getImg());
    }

    @Test
    public void deleteById() {
        productService.deleteById(id);
        Assert.assertNull(productService.getById(id));
    }

}