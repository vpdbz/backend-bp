package com.codingame.backendbp.bpaccountservice.dao;

import java.util.List;
import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingame.backendbp.bpaccountservice.dao.entity.MovementEntity;

public interface MovementRepository extends JpaRepository<MovementEntity, Long> {
    List<MovementEntity> findByDateBetweenAndAccountEntityClientName(Timestamp dateIni, Timestamp dateEnd, String clientName);
}
