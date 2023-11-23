package com.codingame.backendbp.bpaccountservice.dto;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(
        @Range(min = 1, message = "El numero es obligatorio") @JsonProperty("numero") long number,
        @NotBlank(message = "El tipo es obligatorio") @JsonProperty("tipo") String type,
        @NotNull(message = "El saldo no es valido") @JsonProperty("saldo") Long balance,
        @NotBlank(message = "El cliente es obligatorio") @JsonProperty("cliente") String client,
        @NotNull(message = "El estado no es valido") @JsonProperty("estado") Boolean status) {

}
