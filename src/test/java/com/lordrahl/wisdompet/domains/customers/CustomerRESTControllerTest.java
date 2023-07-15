package com.lordrahl.wisdompet.domains.customers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        when(customerService.getAllCustomers("")).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void createNewCustomer() throws Exception {
        String customerRequest = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "phone": "123123123",
                    "address": "johns address"
                }
                """;
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isCreated());
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