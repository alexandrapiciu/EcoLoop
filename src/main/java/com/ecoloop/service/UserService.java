package com.ecoloop.service;

import com.ecoloop.model.User;
import org.springframework.stereotype.Service;

// Provides a single hardcoded demo user. No authentication in this MVP.
@Service
public class UserService {

    private final User currentUser = new User(1L, "Demo User", "demo@ecoloop.app", "Bucharest");

    public User getCurrentUser() {
        return currentUser;
    }
}
