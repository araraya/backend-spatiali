package com.example.backendspatiali.refreshToken.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;
    @GeneratedValue
    private UUID refreshToken;
    private String username;
}
