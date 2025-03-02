package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.responses.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
    OrderResponse updateOrder(OrderDTO orderDTO);
    OrderResponse deleteOrder(String orderId);
    OrderResponse getOrder(String orderId);
    List<OrderResponse> getAllOrders(Long userId);


}

