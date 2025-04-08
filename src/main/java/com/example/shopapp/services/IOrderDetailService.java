package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail getOrderDetailById(Long id) throws Exception;
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;
    List<OrderDetail> findByOrderId(Long orderId);
    void deleteOrderDetailById(Long id);
    OrderDetail updateOrderDetailById(Long id,OrderDetailDTO orderDetailDTO) throws DataNotFoundException;

}
