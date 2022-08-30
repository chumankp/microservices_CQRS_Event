package com.ckp.user.cmd.api.commands;

import com.ckp.user.core.models.User;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;
}
