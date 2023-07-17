package com.lordrahl.wisdompet.domains.customers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.27");

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;


    @BeforeAll
    static void setUp() {
        mysql.start();
    }

    @AfterAll
    static void tearDown() {
        mysql.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        System.out.println(mysql.getJdbcUrl());
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void canSaveAndFindCustomer() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", "123123123", "johns address");
        customerRepository.save(customer);
//        entityManager.persistAndFlush(new Customer(1L, "John", "Doe", "john.doe@example.com", "123123123", "johns address"));
//        entityManager.clear();
//
        Customer foundCustomer = customerRepository.findByEmail(customer.getEmail());
        assertEquals(customer, foundCustomer);

//        getJson();
    }

}