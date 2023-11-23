package com.codingame.backendbp.bpaccountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountResponse(
    long id,
    @JsonProperty("numero") long number,
    @JsonProperty("tipo") String type,
    @JsonProperty("saldo")long balance,
    @JsonProperty("id cliente")long clientId,
    @JsonProperty("cliente")String clientName,
    @JsonProperty("estado")boolean status) {

}
