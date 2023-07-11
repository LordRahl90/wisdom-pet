package com.lordrahl.wisdompet.domains.customers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {
    public Long customerId;
    public String firstName;
    public String lastName;
    public String emailAddress;
    public String phoneNumber;
    public String address;
}
