package com.codingame.backendbp.bpaccountservice.business;

import com.codingame.backendbp.bpaccountservice.dto.AccountResponse;
import com.codingame.backendbp.bpaccountservice.exception.AsyncException;
import com.codingame.backendbp.bpaccountservice.exception.NotFoundException;
import com.codingame.backendbp.bpaccountservice.model.Account;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codingame.backendbp.bpaccountservice.client.ClientRest;
import com.codingame.backendbp.bpaccountservice.client.model.ClientResponse;
import com.codingame.backendbp.bpaccountservice.dao.AccountRepository;
import com.codingame.backendbp.bpaccountservice.dao.entity.AccountEntity;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private ClientRest clientRest;

    public AccountService(AccountRepository accountRepository, ClientRest clientRest) {
        this.accountRepository = accountRepository;
        this.clientRest = clientRest;
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(entity -> mapToAccountResponse(entity))
                .collect(Collectors.toList());
    }

    public AccountResponse getAccountById(Long id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        if (accountEntity.isPresent()) {
            return mapToAccountResponse(accountEntity.get());
        } else {
            throw new NotFoundException("La cuenta no existe con id:" + id);
        }
    }

    public Optional<AccountEntity> getAccountByNumberAndType(long number, String type) {
        return accountRepository.findByNumberAndType(number, type);
    }

    public AccountResponse saveAccount(Account account) {
        ClientResponse clientResponse = getClient(account.getClientName());
        if (clientResponse != null) {
            account.setClientId(clientResponse.id());
            AccountEntity accountEntity = accountRepository.save(new AccountEntity(account));
            return mapToAccountResponse(accountEntity);
        } else {
            throw new NotFoundException("El cliente no existe con el nombre: " + account.getClientName());
        }
    }

    public AccountResponse updateAccount(Long id, Account account) {
        if (accountRepository.existsById(id)) {
            account.setId(id);
            ClientResponse clientResponse = getClient(account.getClientName());
            if (clientResponse != null) {
                account.setClientId(clientResponse.id());
                AccountEntity accountEntity = accountRepository.save(new AccountEntity(account));
                return mapToAccountResponse(accountEntity);
            } else {
                throw new NotFoundException("El cliente no existe con el nombre: " + account.getClientName());
            }
        } else {
            throw new NotFoundException("La cuenta no existe con id: " + id);
        }
    }

    public AccountResponse patchAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .map(accountFound -> {
                    if (account.getClientName() != null) {
                        ClientResponse clientResponse = getClient(account.getClientName());
                        if (clientResponse != null) {
                            accountFound.setClientId(clientResponse.id());
                            accountFound.setClientName(clientResponse.name());
                        } else {
                            throw new NotFoundException(
                                    "El cliente no existe con el nombre: " + account.getClientName());
                        }
                    }
                    if (account.getNumber() != null) {
                        accountFound.setNumber(account.getNumber());
                    }
                    if (account.getType() != null) {
                        accountFound.setType(account.getType());
                    }
                    if (account.getBalance() != null) {
                        accountFound.setBalance(account.getBalance());
                    }
                    if (account.isStatus() != null) {
                        accountFound.setStatus(account.isStatus());
                    }

                    AccountEntity accountEntity = accountRepository.save(accountFound);
                    return mapToAccountResponse(accountEntity);
                })
                .orElseThrow(() -> new NotFoundException("La cuenta no existe con id: " + id));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    private AccountResponse mapToAccountResponse(AccountEntity accountEntity) {
        return new AccountResponse(
                accountEntity.getId(),
                accountEntity.getNumber(),
                accountEntity.getType(),
                accountEntity.getBalance(),
                accountEntity.getClientId(),
                accountEntity.getClientName(),
                accountEntity.isStatus());
    }

    private ClientResponse getClient(String name) {
        CompletableFuture<ClientResponse> clientResponse = clientRest.getClientByName(name);
        CompletableFuture.allOf(clientResponse).join();
        try {
            return clientResponse.get();
        } catch (Exception e) {
            throw new AsyncException("Error al obtener el cliente: " + name);
        }
    }

}
