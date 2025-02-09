package com.example.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class userDTO {
    @JsonProperty("fullname")
    String fullName;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is empty")
    String phoneNumber;
    String address;
    @NotBlank(message = "Password can not be blank")
    String password;
    @JsonProperty("retype_password")
    String retypePassword;
    @JsonProperty("date_of_birth")
    Date dateOfBirth;
    @JsonProperty("facebook_account_id")
    int facebookAccountId;
    @JsonProperty("google_account_id")
    int googleAccountId;

    @JsonProperty("role_id")
    int roleId;
}
