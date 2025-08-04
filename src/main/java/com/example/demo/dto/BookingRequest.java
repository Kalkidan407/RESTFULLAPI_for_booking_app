package com.example.demo.dto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private Long serviceId;
    private LocalDateTime dateTime;
}



