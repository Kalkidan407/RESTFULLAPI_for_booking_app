package com.example.demo.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.*;

import javax.swing.text.html.parser.Entity;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Service;
import com.example.demo.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor

public class ServiceController {

    private final OrderController bookingController;
    private final ServiceRepository serviceRepository;

    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        Service saved = serviceRepository.save(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Service> getAll() {
        return serviceRepository.findAll();
    }


    @GetMapping("/{id}")
    public  EntityModel<Service> getServiceIdEntities(@PathVariable("id") Long id) {
         Service services =  serviceRepository.findById(id)
                       .orElseThrow(() -> new RuntimeException("Sevices not found with id:" + id) );
    
           return EntityModel.of(
            services,
           
           linkTo(methodOn(ServiceController.class).getServiceIdEntities(id)).withSelfRel(),
          linkTo(methodOn(ServiceController.class).getAll()).withRel("services")
           
           );
      }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable("id") Long id, @RequestBody Service request){
        Service service = serviceRepository.findById(id).orElseThrow();
        service.setName(request.getName());
        service.setPrice(request.getPrice());
        return ResponseEntity.ok(serviceRepository.save(service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
        return serviceRepository.findById(id).map(
            service -> {
                serviceRepository.delete(service);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchServices(@PathVariable("id") Long id, @RequestBody Service request) {
        return serviceRepository.findById(id).map(
            service -> {
                if(request.getName() != null){
                    service.setName(request.getName());
                }
                if(request.getPrice()  != null){
                    service.setPrice(request.getPrice());
                }
                return ResponseEntity.ok(serviceRepository.save(service));
            }).orElse(ResponseEntity.notFound().build());
        
    }
}




