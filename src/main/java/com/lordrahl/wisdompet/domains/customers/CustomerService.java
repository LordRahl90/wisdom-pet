package com.lordrahl.wisdompet.domains.customers;

import com.lordrahl.wisdompet.web.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers(String filterEmail) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        if (StringUtils.hasLength(filterEmail)) {
            Customer customer = customerRepository.findByEmail(filterEmail);
            customerDTOList.add(this.translateDBToWeb(customer));
        } else {
            Iterable<Customer> customers = customerRepository.findAll();
            customers.forEach(customer -> {
                customerDTOList.add(this.translateDBToWeb(customer));
            });
        }
        return customerDTOList;
    }

    public CustomerDTO getCustomer(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new NotFoundException("customer not found with this ID");
        }
        return translateDBToWeb(customerOptional.get());
    }

    public CustomerDTO createOrUpdateCustomer(CustomerDTO customerRequest) {
        Customer customer = translateWebToDB(customerRequest);
        return translateDBToWeb(customerRepository.save(customer));
    }

    public void deleteCustomer(Long customerId) {
        this.customerRepository.deleteById(customerId);
    }

    private Customer translateWebToDB(CustomerDTO customerRequest) {
        return Customer.builder()
                .id(customerRequest.getCustomerId())
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmailAddress())
                .phone(customerRequest.getPhoneNumber())
                .address(customerRequest.getAddress())
                .build();
    }

    private CustomerDTO translateDBToWeb(Customer customer) {
        return CustomerDTO.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .emailAddress(customer.getEmail())
                .phoneNumber(customer.getPhone())
                .address(customer.getAddress())
                .build();
    }
}
