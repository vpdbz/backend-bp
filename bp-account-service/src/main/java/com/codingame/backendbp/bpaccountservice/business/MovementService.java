package com.codingame.backendbp.bpaccountservice.business;

import com.codingame.backendbp.bpaccountservice.dto.MovementResponse;
import com.codingame.backendbp.bpaccountservice.exception.NoFundsException;
import com.codingame.backendbp.bpaccountservice.exception.NotFoundException;
import com.codingame.backendbp.bpaccountservice.model.Account;
import com.codingame.backendbp.bpaccountservice.model.Movement;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingame.backendbp.bpaccountservice.dao.AccountRepository;
import com.codingame.backendbp.bpaccountservice.dao.MovementRepository;
import com.codingame.backendbp.bpaccountservice.dao.entity.AccountEntity;
import com.codingame.backendbp.bpaccountservice.dao.entity.MovementEntity;

@Service
public class MovementService {

    private MovementRepository movementRepository;
    private AccountRepository accountRepository;

    public MovementService(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
    }

    public List<MovementResponse> getAllMovements() {
        return movementRepository.findAll().stream()
                .map(entity -> mapToMovementResponse(entity))
                .collect(Collectors.toList());
    }

    public MovementResponse getMovementById(Long id) {
        Optional<MovementEntity> movementEntity = movementRepository.findById(id);
        if (movementEntity.isPresent()) {
            return mapToMovementResponse(movementEntity.get());
        } else {
            throw new NotFoundException("El movimiento no existe con id:" + id);
        }
    }

    @Transactional
    public MovementResponse saveMovement(Movement movement) {
        Optional<AccountEntity> accountEntity = accountRepository.findByNumberAndType(movement.getAccount().getNumber(), movement.getAccount().getType());
        if (accountEntity.isPresent()) {
            if (accountEntity.get().getBalance() + movement.getAmount() < 0) {
                throw new NoFundsException("Saldo no disponible");
            }
            movement.setAccount(new Account(accountEntity.get()));
            movement.setInitialBalance(accountEntity.get().getBalance());
            
            accountEntity.get().setBalance(accountEntity.get().getBalance() + movement.getAmount());
            accountRepository.save(accountEntity.get());

            MovementEntity movementEntity = movementRepository.save(new MovementEntity(movement));
            return mapToMovementResponse(movementEntity);
        } else {
            throw new NotFoundException("La cuenta no existe");
        }
    }

    @Transactional
    public MovementResponse updateMovement(Long id, Movement movement) {
        Optional<MovementEntity> oldMovementEntity = movementRepository.findById(id);
        if (oldMovementEntity.isPresent()) {
            movement.setId(id);
            Optional<AccountEntity> accountEntity = accountRepository.findByNumberAndType(movement.getAccount().getNumber(), movement.getAccount().getType());
            if (accountEntity.isPresent()) {
                long diffPreviousAmount = movement.getAmount() - oldMovementEntity.get().getAmount();
                if (accountEntity.get().getBalance() + diffPreviousAmount < 0) {
                    throw new NoFundsException("Saldo no disponible");
                }

                accountEntity.get().setBalance(accountEntity.get().getBalance() + diffPreviousAmount);
                accountRepository.save(accountEntity.get());

                movement.setAccount(new Account(accountEntity.get()));
                movement.setInitialBalance(oldMovementEntity.get().getInitialBalance());
                MovementEntity movementEntity = movementRepository.save(new MovementEntity(movement));
                return mapToMovementResponse(movementEntity);
            } else {
                throw new NotFoundException("La cuenta no existe");
            }
        } else {
            throw new NotFoundException("El movimiento no existe con id: " + id);
        }
    }

    @Transactional
    public MovementResponse patchMovement(Long id, Movement movement) {
        return movementRepository.findById(id)
                .map(movementFound -> {
                    if (movement.getAmount() != null) {
                        Optional<AccountEntity> accountEntity = accountRepository.findById(movementFound.getAccountEntity().getId());
                        long diffPreviousAmount = movement.getAmount() - movementFound.getAmount();
                        if (accountEntity.get().getBalance() + diffPreviousAmount < 0) {
                            throw new NoFundsException("Saldo no disponible");
                        }
                        accountEntity.get().setBalance(accountEntity.get().getBalance() + diffPreviousAmount);
                        accountRepository.save(accountEntity.get());

                        movementFound.setAmount(movement.getAmount());
                    }
                    if (movement.getDate() != null) {
                        movementFound.setDate(Timestamp.valueOf(movement.getDate().atStartOfDay()));
                    }

                    MovementEntity movementEntity = movementRepository.save(movementFound);
                    return mapToMovementResponse(movementEntity);
                })
                .orElseThrow(() -> new NotFoundException("El movimiento no existe con id: " + id));
    }

    @Transactional
    public void deleteMovement(Long id) {
        Optional<MovementEntity> movementEntity = movementRepository.findById(id);
        if (movementEntity.isPresent()) {
            Optional<AccountEntity> accountEntity = accountRepository.findById(movementEntity.get().getAccountEntity().getId());
            if (accountEntity.isPresent()) {
                accountEntity.get().setBalance(accountEntity.get().getBalance() - movementEntity.get().getAmount());
                accountRepository.save(accountEntity.get());
            }

            movementRepository.deleteById(id);
        }
    }

    private MovementResponse mapToMovementResponse(MovementEntity movementEntity) {
        return new MovementResponse(
                movementEntity.getId(),
                movementEntity.getAccountEntity().getNumber(),
                movementEntity.getDate().toLocalDateTime().toLocalDate(),
                movementEntity.getAccountEntity().getType(),
                movementEntity.getAmount(),
                movementEntity.getInitialBalance());
    }

}
