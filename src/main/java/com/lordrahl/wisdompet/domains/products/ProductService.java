package com.lordrahl.wisdompet.domains.products;

import com.lordrahl.wisdompet.web.errors.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return toDTO(productRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("cannot find product with id: " + id)
                ));
    }

    public ProductDTO createOrUpdateProduct(ProductDTO productDTO) {
        Product product = toDBEntity(productDTO);
        return toDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private Product toDBEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .vendorId(productDTO.getVendorId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .vendorId(product.getVendorId())
                .price(product.getPrice())
                .name(product.getName())
                .build();
    }

}
