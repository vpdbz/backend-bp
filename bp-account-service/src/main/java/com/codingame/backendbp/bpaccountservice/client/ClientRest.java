package com.codingame.backendbp.bpaccountservice.client;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.codingame.backendbp.bpaccountservice.client.model.ClientResponse;
import com.codingame.backendbp.bpaccountservice.client.model.BaseResponse;

@Component
public class ClientRest {

    @Value("${app.client.url}")
    private String clientURL;

    @Async
    public CompletableFuture<ClientResponse> getClientByName(String name) {
        ClientResponse clientResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/nombre/%s", clientURL, name);

        ResponseEntity<BaseResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, BaseResponse.class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null && response.getBody().data() != null) {
            clientResponse = response.getBody().data();
        }

        return CompletableFuture.completedFuture(clientResponse);
    }
}
