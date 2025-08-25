package com.example.demo.controller;
import java.util.*;
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
    public ResponseEntity<?> getServiceIdEntities(@PathVariable("id") Long id) {
        return serviceRepository.findById(id)
         .map(ResponseEntity::ok)
         .orElse(ResponseEntity.notFound().build());
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




