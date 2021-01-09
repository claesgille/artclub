package se.mindstar.artclub;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.mindstar.artclub.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class UserRepositoryIT {
    @Autowired
    private UserRepository repository;

    @Test
    public void testAdd() {
        User user = new User(2000L, "test@test.com", false);
        user = (User)repository.save(user);
        Assertions.assertNotNull(user);
    }
}
