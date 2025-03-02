package com.example.shopapp.services;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.OrderRepository;
import com.example.shopapp.repositories.UserRepository;
import com.example.shopapp.responses.OrderResponse;
import com.project.shopapp.models.OrderStatus;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        // tim xe user_id co ton tai hay khong:
        Long userId = Long.valueOf(orderDTO.getUserId());
        User user =  userRepository.findUserById(userId);
        // chuyen doi orderdto sang order de luu db:
        // dung thu vien model maper
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper-> mapper.skip(Order::setId));
        Order order = new Order();
        modelMapper.map(orderDTO,order);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);

        // kiem tra shipping date >= ngay hom nay
        LocalDate shippingDate = orderDTO.getShippingDate() == null ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Shipping Date Not Found");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        modelMapper.typeMap(Order.class, OrderResponse.class)
                .addMappings(mapper -> mapper.map(src -> src.getUser().getId(), OrderResponse::setUserId));
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        return orderResponse;
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException {
        Order existingOrder = orderRepository.findOrderById(id);
        User user =  userRepository.findUserById(orderDTO.getUserId());
        if(existingOrder == null ||user == null) {
            throw new DataNotFoundException("Order or User khong ton tai");
        }
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper-> mapper.skip(Order::setId));
        Order order = new Order();
        modelMapper.map(orderDTO,order);
        order.setUser(user);
        order.setActive(true);
        return orderRepository.save(order);
    }

    @Override
    public OrderResponse deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findOrderById(id);

    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findOrdersByUserIdWithUser(userId);
    }

}