package com.ckp.user.query.api.handlers;

import com.ckp.user.core.events.UserRegisteredEvent;
import com.ckp.user.core.events.UserRemovedEvent;
import com.ckp.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
