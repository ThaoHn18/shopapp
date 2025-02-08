package com.example.shopapp.controller;

import com.example.shopapp.dtos.ProductDTO;
import com.vaadin.pro.licensechecker.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
//@Validated
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getAllProducts(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "limit") int limit
    ) {
        return ResponseEntity.ok("getproducts");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String productId) {
        return ResponseEntity.ok("procductId: "+ productId);
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                             BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages=result.getFieldErrors()
                    .stream()
                    .map(FieldError:: getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);

        }
         return ResponseEntity.ok("tao thanh cong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok("xoa thanh cong");
    }

}
