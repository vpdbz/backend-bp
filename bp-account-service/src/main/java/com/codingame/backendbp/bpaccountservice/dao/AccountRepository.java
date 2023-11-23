package com.codingame.backendbp.bpaccountservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingame.backendbp.bpaccountservice.dao.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByNumberAndType(long number, String type);
}
