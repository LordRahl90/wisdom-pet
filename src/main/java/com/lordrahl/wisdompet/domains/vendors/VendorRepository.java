package com.lordrahl.wisdompet.domains.vendors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByEmail(String email);
}
