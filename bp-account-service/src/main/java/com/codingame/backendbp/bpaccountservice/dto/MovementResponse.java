package com.codingame.backendbp.bpaccountservice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public record MovementResponse(
        long id,
        @JsonProperty("numero") long number,
        @JsonFormat(pattern = "dd/MM/yyyy") @JsonProperty("fecha") LocalDate date,
        @JsonProperty("tipo") String type,
        @JsonProperty("movimiento") long amount,
        @JsonProperty("saldo inicial") long initialBalance) {

}
