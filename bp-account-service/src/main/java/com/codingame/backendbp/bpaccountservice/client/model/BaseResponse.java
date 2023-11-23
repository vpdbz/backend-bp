package com.codingame.backendbp.bpaccountservice.client.model;

import java.util.List;

public record BaseResponse (
        Integer status,
        List<String> errors,
        String message,
        ClientResponse data) {

}
