package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
         
    Payment savePayment = paymentRepository.save(payment);
        return ResponseEntity.status( HttpStatus.CREATED).body(savePayment);
    }
    
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();

    }
    
    @GetMapping("/{id}")
    public EntityModel<Payment> getPaymentId( @PathVariable("id") Long id) {

        Payment payment = paymentRepository.findById(id)
                      .orElseThrow( () -> new RuntimeException("Payment not found with id" + id));

        return EntityModel.of(payment,
        linkTo(methodOn(PaymentController.class).getPaymentId(id)).withRel("payment"),
        linkTo(methodOn(PaymentController.class).getAllPayments()).withSelfRel()
        );
        
    }}
    


