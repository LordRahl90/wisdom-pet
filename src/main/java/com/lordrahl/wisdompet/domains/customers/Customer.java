package com.lordrahl.wisdompet.domains.customers;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CUSTOMERS")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;


    @Column(name = "EMAIL")
    private String email;


    @Column(name = "PHONE")
    private String phone;


    @Column(name = "ADDRESS")
    private String address;
}
