package com.lordrahl.wisdompet.domains.customers;


import com.lordrahl.wisdompet.domains.utility.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void createCustomer() throws Exception {
        String customerRequest = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "emailAddress": "john.doe@example.com",
                    "phoneNumber": "123123123",
                    "address": "johns address"
                }
                """;

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.emailAddress").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("123123123"))
                .andDo(print());
    }
}
