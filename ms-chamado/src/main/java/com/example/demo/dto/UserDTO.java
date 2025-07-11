package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
    private Integer id;
    private String email;

    @NotNull(message = "ID nao pode ser nulo")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @NotBlank(message = "Email nao pode ser vazio")
    public String getEmail() {
        return email;
    }
}