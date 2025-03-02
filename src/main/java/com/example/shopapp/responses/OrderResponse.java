package com.example.shopapp.responses;

import com.example.shopapp.models.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse extends BaseResponse {
    Long id;
    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("fullname")
    String fullName;

    @JsonProperty("email")
    String email;

    @JsonProperty("phone_number")
    String phoneNumber;

    @JsonProperty("address")
    String address;

    @JsonProperty("note")
    String note;

    @JsonProperty("order_date")
    Date orderDate;

    @JsonProperty("status")
    String status;

    @JsonProperty("total_money")
    Float totalMoney;

    @JsonProperty("shipping_method")
    String shippingMethod;

    @JsonProperty("shipping_address")
    String shippingAddress;

    @JsonProperty("shipping_date")
    LocalDate shippingDate;

    @JsonProperty("tracking_number")
    String trackingNumber;

    @JsonProperty("payment_method")
    String paymentMethod;

    @JsonProperty("payment_status")
    String paymentStatus;

    @JsonProperty("payment_date")
    LocalDate paymentDate;

    @JsonProperty("active")
    Boolean active;

}
