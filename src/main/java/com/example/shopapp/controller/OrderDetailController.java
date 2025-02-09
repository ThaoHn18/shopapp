package com.example.shopapp.controller;

import com.example.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        try{
            return ResponseEntity.ok().body(orderDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOrderDetail(@PathVariable Long id) {
//        try{
//            return ResponseEntity.ok().body("lay duoc id:"+id);
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(e.getMessage());
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable("id") Long orderId) {
        try {
            return ResponseEntity.ok().body("lay thanh cong"+orderId);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id, @Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        try{
            return ResponseEntity.ok().body("update thanh cong"+id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("delete thanh cong"+id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
