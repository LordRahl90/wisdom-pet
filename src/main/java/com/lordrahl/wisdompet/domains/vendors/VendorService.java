package com.lordrahl.wisdompet.domains.vendors;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<VendorDTO> getVendors() {
        return vendorRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public VendorDTO getVendor(Long id) {
        return toDTO(vendorRepository.findById(id).orElseThrow());
    }

    public VendorDTO createOrUpdateVendor(VendorDTO vendorDTO) {
        Vendor vendor = toDBEntity(vendorDTO);
        return toDTO(vendorRepository.save(vendor));
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    private Vendor toDBEntity(VendorDTO vendorDTO) {
        return Vendor.builder()
                .id(vendorDTO.getId())
                .name(vendorDTO.getName())
                .contact(vendorDTO.getContact())
                .phone(vendorDTO.getPhone())
                .email(vendorDTO.getEmail())
                .address(vendorDTO.getAddress())
                .build();
    }

    private VendorDTO toDTO(Vendor vendor) {
        return VendorDTO.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .contact(vendor.getContact())
                .phone(vendor.getPhone())
                .email(vendor.getEmail())
                .address(vendor.getAddress())
                .build();
    }
}
