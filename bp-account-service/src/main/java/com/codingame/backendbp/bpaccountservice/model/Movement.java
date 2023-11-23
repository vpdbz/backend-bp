package com.codingame.backendbp.bpaccountservice.model;

import java.time.LocalDate;

import com.codingame.backendbp.bpaccountservice.dto.MovementPatchRequest;
import com.codingame.backendbp.bpaccountservice.dto.MovementRequest;

public class Movement {
    private long id;
    private long accountId;
    private Long initialBalance;
    private Long amount;
    private LocalDate date;
    private Account account;

    public Movement() {
    }

    public Movement(MovementRequest movementRequest) {
        this.amount = movementRequest.amount();
        this.date = movementRequest.date();
        this.account = new Account();
        this.account.setType(movementRequest.type());
        this.account.setNumber(movementRequest.number());
    }

    public Movement(MovementPatchRequest movementPatchRequest) {
        this.amount = movementPatchRequest.amount();
        this.date = movementPatchRequest.date();
        this.account = new Account();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Long getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Long initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
