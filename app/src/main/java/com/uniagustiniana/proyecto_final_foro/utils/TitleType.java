package com.uniagustiniana.proyecto_final_foro.utils;

public enum TitleType {
    ERROR("¡Error!"),
    SUCCESS("¡Bien!"),
    WARNING("¡Advertencia!");

    private final String title;

    TitleType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
