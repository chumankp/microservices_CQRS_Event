package com.ckp.user.query.api.handlers;

import com.ckp.user.core.events.UserRegisteredEvent;
import com.ckp.user.core.events.UserRemovedEvent;
import com.ckp.user.core.events.UserUpdatedEvent;
import com.ckp.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void on(UserRegisteredEvent userRegisteredEvent) {
        userRepository.save(userRegisteredEvent.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent userUpdatedEvent) {
        userRepository.save(userUpdatedEvent.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent userRemovedEvent) {
        userRepository.deleteById(userRemovedEvent.getId());
    }
}
