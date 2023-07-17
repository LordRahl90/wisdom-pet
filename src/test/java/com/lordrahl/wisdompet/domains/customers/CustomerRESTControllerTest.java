package com.lordrahl.wisdompet.domains.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerRESTController.class)
class CustomerRESTControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCustomers() throws Exception {
        List<CustomerDTO> customerDTOS = List.of(
                new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "123123123", "johns address"),
                new CustomerDTO(2L, "Jane", "Doe", "jane.doe@example.com", "123123123", "jane's address")
        );
        when(customerService.getAllCustomers(any())).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName").value("John"))
                .andExpect(jsonPath("$.[0].lastName").value("Doe"))
                .andExpect(jsonPath("$.[0].emailAddress").value("john.doe@example.com"))
                .andExpect(jsonPath("$.[0].phoneNumber").value("123123123"))
                .andExpect(jsonPath("$.[1].firstName").value("Jane"));
    }

    @Test
    void createNewCustomer() throws Exception {
        String customerRequest = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "emailAddress": "john.doe@example.com",
                    "phoneNumber": "123123123",
                    "address": "johns address"
                }
                """;

        when(customerService.createOrUpdateCustomer(any(CustomerDTO.class))).then(invocationOnMock -> {
            CustomerDTO customerDTO = invocationOnMock.getArgument(0);
            customerDTO.setCustomerId(1L);
            return customerDTO;
        });
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.emailAddress").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("123123123"));
    }


    @Test
    void createNewCustomer_with_invalid_request() throws Exception {
        String customerRequest = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "XXXXXXXXXXXXXXXXXXXX",
                    "phone": "123123123",
                    "address": "johns address",
                },
                """;
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCustomerById() {
    }
}