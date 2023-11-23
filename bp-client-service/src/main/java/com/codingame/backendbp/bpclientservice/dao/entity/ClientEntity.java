package com.codingame.backendbp.bpclientservice.dao.entity;

import com.codingame.backendbp.bpclientservice.model.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "client", schema = "public",
                indexes = {@Index(name = "idx_client_name", columnList = "name")},
                uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class ClientEntity extends PersonEntity{
    private String password;
    private boolean status;

    public ClientEntity() {
    }

    public ClientEntity(Client client) {
        super(client);
        this.password = client.getPassword();
        this.status = client.isStatus();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}