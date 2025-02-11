package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "token", nullable = false)
    String token;

    @Column(name = "token_type", length = 50)
    String tokenType;

    @Column(name = "expiration_date")
    LocalDateTime expirationDate;

    boolean revoked;
    boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
