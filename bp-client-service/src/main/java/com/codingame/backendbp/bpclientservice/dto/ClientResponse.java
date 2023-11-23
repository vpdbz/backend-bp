package com.codingame.backendbp.bpclientservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientResponse(
    long id,
    @JsonProperty("nombre") String name,
    @JsonProperty("genero") String gender,
    @JsonProperty("edad") int age,
    @JsonProperty("direccion") String address,
    @JsonProperty("telefono") String phone,
    @JsonProperty("estado") boolean status
) {
    
}
