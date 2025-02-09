package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order orderId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product productId;

    @Column(nullable = false)
    Float price;

    @Column(name = "number_of_products", nullable = false)
    Integer numberOfProducts;

    @Column(name = "total_money", nullable = false)
    Float totalMoney;

    @Column(name = "color", nullable = false)
    String color;


}
