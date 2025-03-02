package com.example.shopapp.controller;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.responses.OrderResponse;
import com.example.shopapp.services.IOrderService;
import com.example.shopapp.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    @PostMapping()
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO,
                                         BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);

            }
            OrderResponse orderResponse =  orderService.createOrder(orderDTO);
            return ResponseEntity.ok().body(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("loi roi");
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrderById(@Valid @PathVariable("user_id") Long userId) {
        try {
            return ResponseEntity.ok("lay danh sach order cua user_id thanh cong");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("lay danh sach order cua user_id thanh cong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody @PathVariable("id") Long orderId , OrderDTO orderDTO) {
        try {
            return ResponseEntity.ok().body("UPdate order thanh cong");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Update order khong thanh cong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long orderId) {
        try {
            return ResponseEntity.ok().body("Delete order thanh cong");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Update order khong thanh cong");
        }
    }

}
