package com.example.shopapp.repositories;

import com.example.shopapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    @Query("SELECT o FROM Order o JOIN FETCH o.user WHERE o.id = :id")
    Order findOrderById(Long id);

    @Query("SELECT o FROM Order o JOIN FETCH o.user WHERE o.user.id = :userId")
    List<Order> findOrdersByUserIdWithUser(@Param("userId") Long userId);




}
