package com.mrj.web.controller.admin.products;

import com.mrj.web.model.InMemoryStore;
import com.mrj.web.model.Product;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class AdminProductsControllerTest {

    @Inject
    @Client("/admin/products")
    HttpClient client;

    @Inject
    InMemoryStore store;

    @DisplayName("A new product can be added using the admin post endpoint")
    @Test
    void aNewProductCanBeAddedUsingTheAdminPostEndpoint() {
        var productToAdd = new Product(1234, "my-test-product", Product.Type.OTHER);

        store.getProducts().remove(productToAdd.id());
        assertNull(store.getProducts().get(productToAdd.id()));

        var response = client.toBlocking().exchange(
                HttpRequest.POST("/", productToAdd),
                Product.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertTrue(response.getBody().isPresent());
        assertEquals(productToAdd.id(), response.getBody().get().id());
        assertEquals(productToAdd.name(), response.getBody().get().name());
        assertEquals(productToAdd.type(), response.getBody().get().type());
    }

    @DisplayName("Adding a product twice results in conflict")
    @Test
    void addingAProductTwiceResultsInConflict() {
        var productToAdd = new Product(1234, "my-test-product", Product.Type.OTHER);

        store.getProducts().remove(productToAdd.id());
        assertNull(store.getProducts().get(productToAdd.id()));

        var response = client.toBlocking().exchange(
                HttpRequest.POST("/", productToAdd),
                Product.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatus());

        var expectedConflict = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().exchange(HttpRequest.POST("/", productToAdd))
        );
        assertEquals(HttpStatus.CONFLICT, expectedConflict.getStatus());
    }

}