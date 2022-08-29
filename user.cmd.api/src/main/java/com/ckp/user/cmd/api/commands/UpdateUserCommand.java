package com.ckp.user.cmd.api.commands;

import com.ckp.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
