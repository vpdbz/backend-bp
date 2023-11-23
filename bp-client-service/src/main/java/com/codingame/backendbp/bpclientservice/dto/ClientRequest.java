package com.codingame.backendbp.bpclientservice.dto;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        @NotBlank(message = "El nombre es obligatorio") @JsonProperty("nombre") String name,
        @NotBlank(message = "El genero es obligatorio") @JsonProperty("genero") String gender,
        @Range(min = 1, max = 150, message = "La edad no es valida") @JsonProperty("edad") int age,
        @NotBlank(message = "La direccion es obligatoria") @JsonProperty("direccion") String address,
        @NotBlank(message = "El telefono es obligatorio") @JsonProperty("telefono") String phone,
        @NotBlank(message = "La contraseña es obligatoria") @JsonProperty("contraseña") String password,
        @NotNull(message = "El estado no es valido") @JsonProperty("estado") Boolean status) {

}
