package com.uniagustiniana.proyecto_final_foro.utils;

public enum Status {

    ACTIVE("Activo"),
    INACTIVE("Inactivo");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
