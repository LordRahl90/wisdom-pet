package com.lordrahl.wisdompet.domains.services;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SERVICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE", columnDefinition = "Decimal(10,2)")
    private double price;
}
