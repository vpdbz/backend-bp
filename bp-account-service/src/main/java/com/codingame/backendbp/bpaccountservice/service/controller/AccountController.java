package com.codingame.backendbp.bpaccountservice.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingame.backendbp.bpaccountservice.business.AccountService;
import com.codingame.backendbp.bpaccountservice.dto.AccountPatchRequest;
import com.codingame.backendbp.bpaccountservice.dto.AccountRequest;
import com.codingame.backendbp.bpaccountservice.exception.BadRequestException;
import com.codingame.backendbp.bpaccountservice.model.Account;
import com.codingame.backendbp.bpaccountservice.service.controller.model.BaseResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cuentas")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllAccounts() {
        return createOkResponse(accountService.getAllAccounts(), null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getAccountById(@PathVariable Long id) {
        return createOkResponse(accountService.getAccountById(id), null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> saveAccount(@RequestBody @Valid AccountRequest accountRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return createOkResponse(accountService.saveAccount(new Account(accountRequest)), null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateAccount(@PathVariable Long id, @RequestBody @Valid AccountRequest accountRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return createOkResponse(accountService.updateAccount(id, new Account(accountRequest)), null, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> patchAccount(@PathVariable Long id, @RequestBody AccountPatchRequest accountPatchRequest) {
        return createOkResponse(accountService.patchAccount(id, new Account(accountPatchRequest)), null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return createOkResponse(null, null, HttpStatus.OK);
    }

    private ResponseEntity<BaseResponse> createOkResponse(Object data, String message, HttpStatus httpStatus) {
        BaseResponse baseResponse = new BaseResponse();
        
        baseResponse.setMessage(message != null ? message : "Operacion Exitosa");
        baseResponse.setStatus(httpStatus.value());
        if (data != null) {
            baseResponse.setData(data);
        }

        return new ResponseEntity<>(baseResponse, httpStatus);
    }
    
}