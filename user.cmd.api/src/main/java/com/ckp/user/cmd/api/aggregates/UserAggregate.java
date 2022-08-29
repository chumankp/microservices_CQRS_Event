package com.ckp.user.cmd.api.aggregates;

import com.ckp.user.cmd.api.commands.RegisterUserCommand;
import com.ckp.user.cmd.api.commands.RemoveUserCommand;
import com.ckp.user.cmd.api.commands.UpdateUserCommand;
import com.ckp.user.core.events.UserRegisteredEvent;
import com.ckp.user.core.events.UserRemovedEvent;
import com.ckp.user.core.events.UserUpdatedEvent;
import com.ckp.user.core.models.User;
import com.ckp.user.core.security.PasswordEncoder;
import com.ckp.user.core.security.PasswordEncoderImpl;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;


@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate(){
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand registerUserCommand){
        var newUser = registerUserCommand.getUser();
        newUser.setId(registerUserCommand.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent.builder()
                .id(registerUserCommand.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handler(UpdateUserCommand updateUserCommand){
        var updatedUser = updateUserCommand.getUser();
        updatedUser.setId(updateUserCommand.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handler(RemoveUserCommand removeUserCommand){
        var event = new UserRemovedEvent();
        event.setId(removeUserCommand.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent userRegisteredEvent) {
        this.id = userRegisteredEvent.getId();
        this.user = userRegisteredEvent.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent userUpdatedEvent) {
        this.user = userUpdatedEvent.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent userRemovedEvent) {
        AggregateLifecycle.markDeleted();
    }


}
