package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
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
    @JoinColumn(name = "category_id")
    Category category;
}
