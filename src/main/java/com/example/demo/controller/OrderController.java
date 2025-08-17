package com.example.demo.controller;

import com.example.demo.dto.BookingRequest;
import com.example.demo.model.Order;
import com.example.demo.model.Service;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class OrderController {

    private   OrderRepository bookingRepository;
    private  UserRepository userRepository; 
    private   ServiceRepository serviceRepository;

   @PostMapping()
public ResponseEntity<Order> createBooking(@RequestBody Order bookingRequest) {
 
    LocalDateTime dateTime = bookingRequest.getDateTime(); 

    User user = userRepository.getById(null);
    Service service = serviceRepository.findById(bookingRequest.getId()).orElseThrow();
   Order request = bookingRepository.save(bookingRequest);



    Order booking = new Order();
    booking.setUser(user);
    booking.setService(service);
    booking.setDateTime(dateTime);

    bookingRepository.save(booking);
    return ResponseEntity.ok(booking);
}

    // GET: List all bookings
    @GetMapping
    public List<Order> getAllBookings() {
        return bookingRepository.findAll();
    }

    // GET: Get single booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Update a booking
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest) {
        Optional<Order> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> user = userRepository.findById(bookingRequest.getUserId());
        Optional<Service> service = serviceRepository.findById(bookingRequest.getServiceId());

        if (user.isEmpty() || service.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid user or service ID");
        }

        Order booking = existingBooking.get();
        booking.setUser(user.get());
        booking.setService(service.get());
        

        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    // DELETE: Delete a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        if (!bookingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.ok("Booking deleted");
    }
}
