package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fullname", length = 100)
    String fullName;

    @Column(name = "phone_number", length = 20, nullable = false)
    String phoneNumber;

    @Column(name = "address", length = 200 )
    String address;

    @Column(name = "password", length = 200, nullable = false)
    String password;

    boolean activate;
    @Column(name = "date_of_birth")
    Date dateOfBirth;
    @Column(name = "facebook_account_id")
    int facobookAccountId;

    @Column(name = "google-account_id")
    int googleAccountId;

    @ManyToOne
    @Column(name = "role_id", nullable = false)
    Role role;


}
