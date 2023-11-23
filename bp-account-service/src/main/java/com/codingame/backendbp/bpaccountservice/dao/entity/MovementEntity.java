package com.codingame.backendbp.bpaccountservice.dao.entity;

import java.sql.Timestamp;


import com.codingame.backendbp.bpaccountservice.model.Movement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movement", schema = "public",
                indexes = {@Index(name = "idx_movement_date", columnList = "date")})
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long initialBalance;
    private long amount;
    private Timestamp date;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private AccountEntity accountEntity;

    public MovementEntity() {
    }

    public MovementEntity(Movement movement) {
        this.id = movement.getId();
        this.initialBalance = movement.getInitialBalance();
        this.amount = movement.getAmount();
        if (movement.getDate() != null) {
            this.date = Timestamp.valueOf(movement.getDate().atStartOfDay());
        } else {
            this.date = new Timestamp(System.currentTimeMillis());
        }
        this.accountEntity = new AccountEntity(movement.getAccount());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(long initialBalance) {
        this.initialBalance = initialBalance;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

}