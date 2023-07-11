package com.lordrahl.wisdompet.domains.services;

import com.lordrahl.wisdompet.web.errors.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceHandler {
    private final ServiceRepository serviceRepository;

    public ServiceHandler(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> serviceDTOS = new ArrayList<>();

        serviceRepository.findAll().forEach(svc -> {
            serviceDTOS.add(toDTO(svc));
        });

        return serviceDTOS;
    }

    public ServiceDTO getService(Long id) {
        Optional<ServiceEntity> serviceEntityOptional = serviceRepository.findById(id);
        if (serviceEntityOptional.isEmpty()) {
            throw new NotFoundException("service not found with the ID");
        }

        return toDTO(serviceEntityOptional.get());
    }

    public ServiceDTO createOrUpdateService(ServiceDTO serviceDTO) {
        ServiceEntity serviceEntity = toDBEntity(serviceDTO);
        serviceEntity = serviceRepository.save(serviceEntity);
        return toDTO(serviceEntity);
    }

    public void deleteService(Long id) {
        Optional<ServiceEntity> serviceEntityOptional = serviceRepository.findById(id);
        if (serviceEntityOptional.isEmpty()) {
            throw new NotFoundException("service not found with the ID");
        }
        serviceRepository.delete(serviceEntityOptional.get());
    }


    private ServiceEntity toDBEntity(ServiceDTO serviceDTO) {
        return new ServiceEntity(
                serviceDTO.getId(),
                serviceDTO.getName(),
                serviceDTO.getPrice());
    }

    private ServiceDTO toDTO(ServiceEntity serviceEntity) {
        return new ServiceDTO(
                serviceEntity.getId(),
                serviceEntity.getName(),
                serviceEntity.getPrice());
    }
}
