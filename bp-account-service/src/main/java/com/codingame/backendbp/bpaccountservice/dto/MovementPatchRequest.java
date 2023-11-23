package com.codingame.backendbp.bpaccountservice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovementPatchRequest(
        @JsonFormat(pattern = "dd/MM/yyyy") @JsonProperty("fecha") LocalDate date,
        @JsonProperty("movimiento") Long amount) {

}
