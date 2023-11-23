package com.codingame.backendbp.bpaccountservice.service.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingame.backendbp.bpaccountservice.business.ReportService;
import com.codingame.backendbp.bpaccountservice.service.controller.model.BaseResponse;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> getReport(
            @RequestParam("fecha_ini") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateIni,
            @RequestParam("fecha_fin") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateEnd,
            @RequestParam("cliente") String client) {
        return createOkResponse(reportService.getAllMovementsByDateAndClient(dateIni, dateEnd, client), null, HttpStatus.OK);
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