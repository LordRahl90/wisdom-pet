package com.lordrahl.wisdompet.domains.vendors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorRestController {

    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<VendorDTO> getAllVendors() {
        return vendorService.getVendors();
    }

    @GetMapping("/{id}")
    public VendorDTO getVendorById(@PathVariable("id") Long id) {
        return vendorService.getVendor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO saveVendor(@RequestBody VendorDTO vendor) {
        return vendorService.createOrUpdateVendor(vendor);
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable("id") Long id, @RequestBody VendorDTO vendor) {
        vendor.setId(id);
        return vendorService.createOrUpdateVendor(vendor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendor(@PathVariable("id") Long id) {
        vendorService.deleteVendor(id);
    }
}
