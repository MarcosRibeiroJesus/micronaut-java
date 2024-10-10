package com.mrj.web.model;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);
    private Map<Integer, Product> products = new HashMap<>();
    private final Faker faker = new Faker();

    @PostConstruct
    public void initialize() {
        IntStream.range(0, 10).forEach(
                this::addProduct
        );
    }

    private void addProduct(int id) {
        var product = new Product(id, faker.coffee().blendName(), Product.Type.COFFEE);
        products.put(id, product);
        LOG.debug("Added Product: {}", product);
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }
}
