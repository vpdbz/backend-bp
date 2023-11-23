package com.codingame.backendbp.bpclientservice.service.controller;

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

import com.codingame.backendbp.bpclientservice.business.ClientService;
import com.codingame.backendbp.bpclientservice.dto.ClientPatchRequest;
import com.codingame.backendbp.bpclientservice.dto.ClientRequest;
import com.codingame.backendbp.bpclientservice.exception.BadRequestException;
import com.codingame.backendbp.bpclientservice.model.Client;
import com.codingame.backendbp.bpclientservice.service.controller.model.BaseResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllClients() {
        return createOkResponse(clientService.getAllClients(), null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getClientById(@PathVariable Long id) {
        return createOkResponse(clientService.getClientById(id), null, HttpStatus.OK);
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<BaseResponse> getClientByName(@PathVariable String name) {
        return createOkResponse(clientService.getClientByName(name), null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> saveClient(@RequestBody @Valid ClientRequest clientRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return createOkResponse(clientService.saveClient(new Client(clientRequest)), null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateClient(@PathVariable Long id,
            @RequestBody @Valid ClientRequest clientRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return createOkResponse(clientService.updateClient(id, new Client(clientRequest)), null, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> patchClient(@PathVariable Long id,
            @RequestBody ClientPatchRequest clientPatchRequest) {
        return createOkResponse(clientService.patchClient(id, new Client(clientPatchRequest)), null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
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