package com.senla.finalTask.service;

import com.senla.finalTask.model.User;
import com.senla.finalTask.model.UserSubscription;
import com.senla.finalTask.repository.UserDetailsRepository;
import com.senla.finalTask.repository.UserSubscriptionRepository;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProfileServiceTest {



    @Test
    public void testChangeSubscriptionStatus() {
        UserDetailsRepository userDetailsRepository = Mockito.mock(UserDetailsRepository.class);
        UserSubscriptionRepository userSubscriptionRepository = Mockito.mock(UserSubscriptionRepository.class);
        ProfileService profileService = new ProfileService(userDetailsRepository, userSubscriptionRepository);
        User channel = new User();
        User subscription = new User();
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setActive(false);
        when(userSubscriptionRepository.findByChannelAndSubscriber(channel, subscription)).thenReturn(userSubscription);
        when(userSubscriptionRepository.save(userSubscription)).thenReturn(userSubscription);
        profileService.changeSubscriptionStatus(channel, subscription);
        assertTrue(userSubscription.isActive());
    }
}