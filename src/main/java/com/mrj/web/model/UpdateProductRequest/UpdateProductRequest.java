package com.mrj.web.model.UpdateProductRequest;

import com.mrj.web.model.Product;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UpdateProductRequest(String name, Product.Type type) {}
