package com.mrj.web.controller.products;

import com.mrj.web.model.Product;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.JsonMapper;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@MicronautTest
class ProductsControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ProductsControllerTest.class);

    @Inject
    @Client("/products")
    private HttpClient client;

    @Inject
    private JsonMapper jsonMapper;

    @DisplayName("Products endpoint returns 10 products")
    @Test
    void productsEndpointReturnsTenProducts() throws IOException {
        var response = client.toBlocking().retrieve("/", JsonNode.class);
        LOG.debug("Retrieved products: {}", logProducts(response));
        assertEquals(10, response.size());
    }

    @DisplayName("Products fetch product by ID")
    @Test
    void productsFetchProductByID() {
        var response = client.toBlocking().retrieve("/0", Product.class);
        assertEquals(0, response.id());
        assertEquals(Product.Type.COFFEE, response.type());
        assertNotNull(response.name());
    }

    @DisplayName("Can limit the amount of products to fetch to 5")
    @Test
    void canLimitTheAmountOfProductsToFetchTo5() throws IOException {
        JsonNode response = client.toBlocking().retrieve("/filter?max=5", JsonNode.class);
        LOG.debug("Retrieved 5 products: {}", logProducts(response));
        assertEquals(5, response.size());
    }

    @DisplayName("Can filter using offset and max limit")
    @Test
    void canFilterUsingOffsetAndMaxLimit  () throws IOException {
        JsonNode response = client.toBlocking().retrieve("/filter?max=2&offset=6", JsonNode.class);
        LOG.debug("Retrieved 2 products starting with offset 6: {}", logProducts(response));
        assertEquals(2, response.size());
        assertEquals(6, response.get(0).get("id").getIntValue());
        assertEquals(7, response.get(1).get("id").getIntValue());
    }

    private String logProducts(JsonNode response) throws IOException {
        return jsonMapper.writeValueAsString(response);
    }
}