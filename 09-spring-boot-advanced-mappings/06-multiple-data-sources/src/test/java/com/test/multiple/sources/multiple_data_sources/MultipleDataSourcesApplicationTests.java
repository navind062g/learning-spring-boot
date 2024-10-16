package com.test.multiple.sources.multiple_data_sources;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.test.multiple.sources.multiple_data_sources.dao.product.ProductRepository;
import com.test.multiple.sources.multiple_data_sources.dao.user.UserRepository;
import com.test.multiple.sources.multiple_data_sources.model.product.Product;
import com.test.multiple.sources.multiple_data_sources.model.user.User;


@SpringBootTest(classes = MultipleDataSourcesApplication.class)
@EnableTransactionManagement
class MultipleDataSourcesApplicationTests {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        //user.setAge(20);
        user = userRepository.save(user);

        assertNotNull(userRepository.findById(user.getId()));
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {
        User user1 = new User();
        user1.setName("John");
        user1.setEmail("john@test.com");
        //user1.setAge(20);
        user1 = userRepository.save(user1);
        assertTrue(userRepository.findById(user1.getId()).isPresent());

        User user2 = new User();
        user2.setName("Tom");
        user2.setEmail("john@test.com");
        //user2.setAge(10);
        try {
            user2 = userRepository.save(user2);
            userRepository.flush();
            fail("DataIntegrityViolationException should be thrown!");
        } catch (final DataIntegrityViolationException e) {
            // Expected
        } catch (final Exception e) {
            fail("DataIntegrityViolationException should be thrown, instead got: " + e);
        }

        assertTrue(userRepository.findById(user2.getId()).isEmpty());
    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Product product = new Product();
        product.setName("Book");
        product.setId(2);
        product.setPrice(20);
        product = productRepository.save(product);

        assertNotNull(productRepository.findById(product.getId()));
    }
}
