package com.example.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderDTO {
    @JsonProperty("user_id")
    @Min(value = 1, message = "user id > 1")
    Long userId;
    @JsonProperty("fullname")
    String fullName;
    String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number not be blank")
    String phoneNumber;
    String address;
    String note;
    @JsonProperty("total_money")
    @Min(value = 0,message = "total money > 0")
    Float totalMoney;
    @JsonProperty("shipping_address")
    String shippingAddress;
    @JsonProperty("shipping_method")
    String shippingMethod;
    @JsonProperty("payment_method")
    String paymentMethod;
    @JsonProperty("shipping_date")
    LocalDate shippingDate;
}
