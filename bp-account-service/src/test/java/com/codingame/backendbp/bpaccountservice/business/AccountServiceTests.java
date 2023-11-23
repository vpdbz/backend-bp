package com.codingame.backendbp.bpaccountservice.business;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.codingame.backendbp.bpaccountservice.dto.AccountResponse;

@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {

  @Autowired
  private AccountService service;

  @Test
  void getAllAccounts() {
    List<AccountResponse> result = service.getAllAccounts();

    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  @Test
  void getAccountById() {
    AccountResponse result = service.getAccountById(1L);

    assertNotNull(result);
    assertEquals(result.clientName(), "Juan Perez");
  }

}