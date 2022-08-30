package com.ckp.user.core.events;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRemovedEvent {
    private String id;
}
