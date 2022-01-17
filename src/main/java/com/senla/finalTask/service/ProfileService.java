package com.senla.finalTask.service;

import com.senla.finalTask.model.User;
import com.senla.finalTask.model.UserSubscription;
import com.senla.finalTask.repository.UserDetailsRepository;
import com.senla.finalTask.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    public ProfileService(UserDetailsRepository userDetailsRepository, UserSubscriptionRepository userSubscriptionRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subcriptions = channel.getSubscribers()
                .stream()
                .filter(subscription ->
                        subscription.getSubscriber().equals(subscriber)
                )
                .collect(Collectors.toList());

        if (subcriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subcriptions);
        }
        return userDetailsRepository.save(channel);
    }

    public List<UserSubscription> getSubscribers(User channel) {
        return userSubscriptionRepository.findByChannel(channel);
    }

    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscription subscription = userSubscriptionRepository.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());

        return userSubscriptionRepository.save(subscription);
    }
}
