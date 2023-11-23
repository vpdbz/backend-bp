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

import com.codingame.backendbp.bpaccountservice.business.MovementService;
import com.codingame.backendbp.bpaccountservice.dto.MovementPatchRequest;
import com.codingame.backendbp.bpaccountservice.dto.MovementRequest;
import com.codingame.backendbp.bpaccountservice.exception.BadRequestException;
import com.codingame.backendbp.bpaccountservice.model.Movement;
import com.codingame.backendbp.bpaccountservice.service.controller.model.BaseResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovementController {

    private MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllMovements() {
        return createOkResponse(movementService.getAllMovements(), null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getMovementById(@PathVariable Long id) {
        return createOkResponse(movementService.getMovementById(id), null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> saveMovement(@RequestBody @Valid MovementRequest movementRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return createOkResponse(movementService.saveMovement(new Movement(movementRequest)), null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateMovement(@PathVariable Long id, @RequestBody @Valid MovementRequest movementRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return createOkResponse(movementService.updateMovement(id, new Movement(movementRequest)), null, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> patchMovement(@PathVariable Long id, @RequestBody MovementPatchRequest movementPatchRequest) {
        return createOkResponse(movementService.patchMovement(id, new Movement(movementPatchRequest)), null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteMovement(@PathVariable Long id) {
        movementService.deleteMovement(id);
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