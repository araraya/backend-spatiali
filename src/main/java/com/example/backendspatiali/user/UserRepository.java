package com.example.backendspatiali.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);
}