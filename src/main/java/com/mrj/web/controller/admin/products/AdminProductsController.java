package com.mrj.web.controller.admin.products;

import com.mrj.web.model.InMemoryStore;
import com.mrj.web.model.Product;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.exceptions.HttpStatusException;
import lombok.RequiredArgsConstructor;

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
                    "Product with id " + product.id() + " already exists");
        }
        return store.addProduct(product);
    }
}
