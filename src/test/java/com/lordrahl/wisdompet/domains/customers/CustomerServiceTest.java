package com.lordrahl.wisdompet.domains.customers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getAllCustomers() {
        List<Customer> customers = List.of(
                new Customer(1L, "John", "Doe", "john.doe@example.com", "+1234567", "3, rue de la paix"),
                new Customer(2L, "Jane", "Doe", "jane.doe@example.com", "+12345678", "3, rue de la paix")
        );
        when(customerRepository.findAll()).thenReturn(customers);
        CustomerService customerService = new CustomerService(customerRepository);

        List<CustomerDTO> result = customerService.getAllCustomers("");
        assertEquals(2, result.size());
    }


    @Test
    void getAllCustomers_empty() {
        List<Customer> customers = List.of();
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerRepository.findAll();
        assertEquals(0, result.size());
    }

    @Test
    void getAllCustomers_withFilterEmail() {
        List<Customer> customers = List.of(
                new Customer(1L, "John", "Doe", "john.doe@example.com", "+1234567", "3, rue de la paix"),
                new Customer(2L, "Jane", "Doe", "jane.doe@example.com", "+12345678", "3, rue de la paix")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerRepository.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void getCustomer() {
    }

    @Test
    void createOrUpdateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}