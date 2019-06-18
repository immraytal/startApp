package startApp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import startApp.entities.User;
import startApp.repository.RoleRepository;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    public void saveUserTest() {
        User user = new User(12L, "test123", "123", false, null,null , null);
        userService.saveUser(user);
        List<User> users = userService.findAll();

        User target = userService.findByUsername("test123");
        Assert.assertEquals(user.getUsername(),target.getUsername());
    }

    @Test
    @Transactional
    public void deleteByUsername() {
        userService.deleteByUsername("test123");
        Assert.assertNull(userService.findByUsername("test123"));
    }
}


