package com.codingame.backendbp.bpaccountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountPatchRequest(
        @JsonProperty("number") Long number,
        @JsonProperty("tipo") String type,
        @JsonProperty("saldo") Long balance,
        @JsonProperty("cliente") String client,
        @JsonProperty("estado") Boolean status) {

}
