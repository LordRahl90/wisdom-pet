package com.lordrahl.wisdompet.domains.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonComponent
public class ProductDTO {
    private Long id;
    @JsonProperty("vendor_id")
    private Long vendorId;
    private String name;
    private double price;
}
