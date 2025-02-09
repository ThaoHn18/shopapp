package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false, length = 350)
    String name;
    Float price;
    @Column(name = "thumbnail", nullable = true, length = 350)
    String thumbnail;

    @Column(name = "description")
    String description;


    @ManyToOne
    @Column(name = "category_id")
    Long categoryId;
}
