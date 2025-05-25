package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewChamado(
    @NotBlank(message = "A ação é obrigatória")
    @NotNull(message = "A ação não pode ser nula")
    String acao,

    @NotBlank(message = "O objeto é obrigatório")
    @NotNull(message = "O objeto não pode ser nulo")
    String objeto,
    
    @NotBlank(message = "O detalhamento é obrigatório")
    @NotNull(message = "O detalhamento não pode ser nulo")
    String detalhamento,

    @NotNull(message = "O id do usuário não pode ser nulo")
    Integer userId
) {

    
}
