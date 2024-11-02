package com.mrj.web.controller.admin.products;

import com.mrj.web.model.InMemoryStore;
import com.mrj.web.model.Product;
import com.mrj.web.model.UpdateProductRequest.UpdateProductRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Controller("/admin/products")
@RequiredArgsConstructor
public class AdminProductsController {

    private final InMemoryStore store;

    @Status(HttpStatus.CREATED)
    @Post(consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public Product addNewProduct(@Body Product product) {
        if (store.getProducts().containsKey(product.id())) {
            throw new HttpStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Product with id %s already exists", product.id()));
        }
        return store.addProduct(product);
    }

    @Put("{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @Body UpdateProductRequest request) {
        var updatedProduct = new Product(id, request.name(), request.type());
        return store.addProduct(updatedProduct);
    }

    @Delete("{id}")
    public Product deleteProduct(@PathVariable Integer id) {
        return store.deleteProduct(id);
    }
}
