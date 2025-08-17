package com.example.demo.service;

import com.example.demo.model.ServiceEntity;
import com.example.demo.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvidedService {

    private final ServiceRepository repository;

    public ProvidedService(ServiceRepository repository) {
        this.repository = repository;
    }

    // Get all services
    public List<ServiceEntity> getAllServices() {
        return repository.findAll();
    }

    // Get a service by ID
    public ServiceEntity getServiceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    // Create a new service
    public ServiceEntity createService(ServiceEntity service) {
        return repository.save(service);
    }

    // Update an existing service
    public ServiceEntity updateService(Long id, ServiceEntity serviceDetails) {
        ServiceEntity service = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));

        service.setName(serviceDetails.getName());
        service.setPrice(serviceDetails.getPrice());
        service.setDescription(serviceDetails.getDescription());

        return repository.save(service);
    }

    // Delete a service
    public void deleteService(Long id) {
        repository.deleteById(id);
    }
}
