package com.codingame.backendbp.bpclientservice.business;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import com.codingame.backendbp.bpclientservice.dao.ClientRepository;
import com.codingame.backendbp.bpclientservice.dao.entity.ClientEntity;
import com.codingame.backendbp.bpclientservice.dto.ClientResponse;

class ClientServiceTest {

  ClientRepository repository;
  ClientService service;

  @BeforeEach
  void setup() {
    repository = Mockito.mock(ClientRepository.class);
    service = new ClientService(repository); 
  }

  @Test
  void getAllClients() {
    Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new ClientEntity()));
    
    List<ClientResponse> result = service.getAllClients();
    
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  @Test
  void getClientById() {
    Mockito.when(repository.findById(1L))
      .thenReturn(Optional.of(new ClientEntity()));

    ClientResponse result1 = service.getClientById(1L);

    assertNotNull(result1);
  }

}