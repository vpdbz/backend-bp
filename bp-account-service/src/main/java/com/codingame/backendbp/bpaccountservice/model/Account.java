package com.codingame.backendbp.bpaccountservice.model;

import com.codingame.backendbp.bpaccountservice.dao.entity.AccountEntity;
import com.codingame.backendbp.bpaccountservice.dto.AccountPatchRequest;
import com.codingame.backendbp.bpaccountservice.dto.AccountRequest;

public class Account {
    private long id;
    private Long number;
    private String type;
    private Long balance;
    private Long clientId;
    private String clientName;
    private Boolean status;

    public Account() {
    }

    public Account(AccountRequest accountRequest) {
        this.number = accountRequest.number();
        this.type = accountRequest.type();
        this.balance = accountRequest.balance();
        this.clientName = accountRequest.client();
        this.status = accountRequest.status();
    }

    public Account(AccountPatchRequest accountPatchRequest) {
        this.number = accountPatchRequest.number();
        this.type = accountPatchRequest.type();
        this.balance = accountPatchRequest.balance();
        this.clientName = accountPatchRequest.client();
        this.status = accountPatchRequest.status();
    }

    public Account(AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.number = accountEntity.getNumber();
        this.type = accountEntity.getType();
        this.balance = accountEntity.getBalance();
        this.clientId = accountEntity.getClientId();
        this.clientName = accountEntity.getClientName();
        this.status = accountEntity.isStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
