package com.mrj.web.controller;

import com.mrj.web.model.InMemoryStore;
import com.mrj.web.model.Product;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller("/products")
public class ProductsController {

    private final InMemoryStore store;

    public ProductsController(InMemoryStore store) {
        this.store = store;
    }

    @Get
    public List<Product> listAll() {
        return new ArrayList<>(store.getProducts().values());
    }

    @Get("{id}")
    public Product getProduct(@PathVariable Integer id) {
        return store.getProducts().get(id);
    }
}

