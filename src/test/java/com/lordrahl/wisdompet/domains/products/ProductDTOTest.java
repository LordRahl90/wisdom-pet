package com.lordrahl.wisdompet.domains.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class ProductDTOTest {

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private JacksonTester<ProductDTO> jacksonTester;


    @Test
    void serialize() throws IOException {
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .vendorId(2L)
                .name("test")
                .price(100.30)
                .build();

        JsonContent<ProductDTO> jsonContent = jacksonTester.write(productDTO);
        System.out.println(jsonContent);
        assertThat(jsonContent).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(jsonContent).extractingJsonPathNumberValue("$.vendor_id").isEqualTo(2);
        assertThat(jsonContent).extractingJsonPathStringValue("$.name").isEqualTo("test");
    }

    @Test
    void deserialize() throws IOException {
        ProductDTO productDTO = jacksonTester.readObject("read.json");
        assertThat(productDTO).isNotNull();
        assertThat(productDTO.getId()).isEqualTo(1234L);
        assertThat(productDTO.getVendorId()).isEqualTo(3241L);
    }

}