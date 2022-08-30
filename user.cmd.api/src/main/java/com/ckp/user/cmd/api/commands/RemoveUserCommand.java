package com.ckp.user.cmd.api.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveUserCommand {
    @TargetAggregateIdentifier
    private String id;
}
