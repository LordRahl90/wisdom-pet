package com.lordrahl.wisdompet.domains.customers;

import com.lordrahl.wisdompet.domains.utility.BaseJPATest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest extends BaseJPATest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void canSaveAndFindCustomer() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", "123123123", "johns address");
        customerRepository.save(customer);
//        entityManager.persistAndFlush(customer);
//        entityManager.clear();
//
        Customer foundCustomer = customerRepository.findByEmail(customer.getEmail());
        assertEquals(customer, foundCustomer);

//        getJson();
    }

}