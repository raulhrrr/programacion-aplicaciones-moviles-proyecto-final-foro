package com.uniagustiniana.proyecto_final_foro.dto;

public class Group {

    private String userUid;
    private String name;
    private String description;
    private String status;

    public Group(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String uid) {
        this.userUid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
