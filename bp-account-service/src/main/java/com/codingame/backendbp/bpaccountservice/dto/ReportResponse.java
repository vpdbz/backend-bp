package com.codingame.backendbp.bpaccountservice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ReportResponse(
        @JsonFormat(pattern = "dd/MM/yyyy") @JsonProperty("fecha") LocalDate date,
        @JsonProperty("cliente") String client,
        @JsonProperty("numero") long number,
        @JsonProperty("tipo") String type,
        @JsonProperty("saldo inicial") long initialBalance,
        @JsonProperty("estado") boolean status,
        @JsonProperty("movimiento") long amount,
        @JsonProperty("saldo disponible") long availableBalance) {

}
