package com.example.demo.dto;

import java.time.LocalDate;

public class OrderRequest {
    private Long userId;
    private Long serviceId;
    private LocalDate orderDate;
    private String status;
    private LocalDate deliveryDate;
}
