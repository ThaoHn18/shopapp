package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.models.Order;
import com.example.shopapp.responses.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
    Order updateOrder(Long id, OrderDTO orderDTO) throws Exception;
    OrderResponse deleteOrder(Long id);
    Order getOrder(Long id);
    List<Order> findByUserId(Long userId);

}

