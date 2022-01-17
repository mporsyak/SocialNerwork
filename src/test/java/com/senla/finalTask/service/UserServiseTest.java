package com.senla.finalTask.service;

import com.senla.finalTask.model.Role;
import com.senla.finalTask.model.User;
import com.senla.finalTask.repository.UserDetailsRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

;import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiseTest {
    @Autowired
    private UserServise userServise;
    @MockBean
    private UserDetailsRepository userDetailsRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUser() {
        User user = new User();
        boolean isUserCreate = userServise.addUser(user);

        Assert.assertTrue(isUserCreate);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userDetailsRepository, Mockito.times(1)).save(user);
    }
}
