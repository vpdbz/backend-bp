package com.codingame.backendbp.bpclientservice.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codingame.backendbp.bpclientservice.dao.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByName(String name);
}
