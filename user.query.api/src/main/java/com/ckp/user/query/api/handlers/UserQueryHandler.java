package com.ckp.user.query.api.handlers;

import com.ckp.user.query.api.dto.UserLookupResponse;
import com.ckp.user.query.api.queries.FindAllUsersQuery;
import com.ckp.user.query.api.queries.FindUserByIdQuery;
import com.ckp.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
