package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "social_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "provider")
    String provider;

    @Column(name = "provider_id", length = 20)
    String providerId;

    @Column(length = 150)
    String email;

    @Column(length = 150)
    String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
