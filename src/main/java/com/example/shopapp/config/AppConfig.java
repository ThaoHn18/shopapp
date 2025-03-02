package com.example.shopapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Đánh dấu đây là một class cấu hình Spring
public class AppConfig {

    @Bean // Định nghĩa một Bean để Spring Boot quản lý
    public ModelMapper modelMapper() {
        return new ModelMapper(); // Trả về một instance của ModelMapper
    }
}
