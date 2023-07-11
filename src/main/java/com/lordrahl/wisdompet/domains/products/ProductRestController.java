package com.lordrahl.wisdompet.domains.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable(name = "id", required = true) Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO product) {
        return productService.createOrUpdateProduct(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO product) {
        product.setId(id);
        return productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
