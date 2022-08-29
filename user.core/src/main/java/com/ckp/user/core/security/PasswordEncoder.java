package com.ckp.user.core.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
