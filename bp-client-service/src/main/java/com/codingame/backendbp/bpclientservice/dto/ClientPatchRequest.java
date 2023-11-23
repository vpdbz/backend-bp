package com.codingame.backendbp.bpclientservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientPatchRequest(
        @JsonProperty("nombre") String name,
        @JsonProperty("genero") String gender,
        @JsonProperty("edad") Integer age,
        @JsonProperty("direccion") String address,
        @JsonProperty("telefono") String phone,
        @JsonProperty("contraseña") String password,
        @JsonProperty("estado") Boolean status) {

}
