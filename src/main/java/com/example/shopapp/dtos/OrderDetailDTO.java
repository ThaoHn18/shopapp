package com.example.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Id must be > 0")
    Long orderId;
    @JsonProperty("product_id")
    @Min(value = 1, message = "Id must be > 0")
    Long productId;
    Long price;
    @JsonProperty("number_of_product")
    @Min(value = 1, message = "numberOfProducts must be > 0")
    int numberOfProducts;
    @JsonProperty("total_money")
    int totalMoney;
    String color;
}
