package com.codingame.backendbp.bpaccountservice.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovementRequest(
        @Range(min = 1, message = "El numero es obligatorio") @JsonProperty("numero") long number,
        @NotNull(message = "La fecha es obligatoria") @JsonFormat(pattern = "dd/MM/yyyy") @JsonProperty("fecha") LocalDate date,
        @NotBlank(message = "El tipo de cuenta es obligatorio") @JsonProperty("tipo") String type,
        @NotNull(message = "El movimiento es obligatorio") @JsonProperty("movimiento") Long amount) {

}
