package com.lordrahl.wisdompet.domains.services;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceRESTController {
    private final ServiceHandler serviceHandler;

    public ServiceRESTController(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }


    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceHandler.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable("id") Long id) {
        return serviceHandler.getService(id);
    }

    @PostMapping
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
        return serviceHandler.createOrUpdateService(serviceDTO);
    }

    @PutMapping("/{id}")
    public ServiceDTO updateService(@PathVariable("id") Long id, @RequestBody ServiceDTO serviceDTO) {
        return serviceHandler.createOrUpdateService(serviceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable("id") Long id) {
        serviceHandler.deleteService(id);
    }

}
