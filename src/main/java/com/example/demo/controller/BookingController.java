package com.example.demo.controller;

import com.example.demo.dto.BookingRequest;
import com.example.demo.model.Booking;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        ServiceEntity service = serviceRepository.findById(request.getServiceId()).orElseThrow();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setDateTime(request.getDateTime());

        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }
}
