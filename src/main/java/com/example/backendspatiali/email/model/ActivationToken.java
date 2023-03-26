package com.example.backendspatiali.email.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivationToken {
    @Id
    @GeneratedValue
    private Long tokenId;
    private String email;
    private UUID activationToken;
}
