package com.codingame.backendbp.bpclientservice.model;

import com.codingame.backendbp.bpclientservice.dto.ClientPatchRequest;
import com.codingame.backendbp.bpclientservice.dto.ClientRequest;

public class Client extends Person {
    private String password;
    private Boolean status;

    public Client() {
    }

    public Client(ClientRequest clientRequest) {
        super(clientRequest);
        this.password = clientRequest.password();
        this.status = clientRequest.status();
    }

    public Client(ClientPatchRequest clientPatchRequest) {
        super(clientPatchRequest);
        this.password = clientPatchRequest.password();
        this.status = clientPatchRequest.status();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
