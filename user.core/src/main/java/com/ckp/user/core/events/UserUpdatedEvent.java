package com.ckp.user.core.events;

import com.ckp.user.core.models.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
