package com.mrj.web.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public record Product(
        Integer id,
        String name,
        Type type
) {
    public enum Type {
        COFFEE, TEA, OTHER
    }
}