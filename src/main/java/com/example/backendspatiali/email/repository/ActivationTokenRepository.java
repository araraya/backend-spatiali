package com.example.backendspatiali.email.repository;


import com.example.backendspatiali.email.model.ActivationToken;
import com.example.backendspatiali.user.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {

    ActivationToken findByEmail(String email);
}
