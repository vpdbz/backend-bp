package com.codingame.backendbp.bpaccountservice.business;

import com.codingame.backendbp.bpaccountservice.dto.ReportResponse;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.codingame.backendbp.bpaccountservice.dao.MovementRepository;
import com.codingame.backendbp.bpaccountservice.dao.entity.MovementEntity;

@Service
public class ReportService {

    private MovementRepository movementRepository;

    public ReportService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public List<ReportResponse> getAllMovementsByDateAndClient(LocalDate dateIni, LocalDate dateEnd,
            String clientName) {
        return movementRepository.findByDateBetweenAndAccountEntityClientName(Timestamp.valueOf(dateIni.atStartOfDay()), Timestamp.valueOf(dateEnd.atStartOfDay()), clientName).stream()
                .map(entity -> mapToReportResponse(entity))
                .collect(Collectors.toList());
    }

    private ReportResponse mapToReportResponse(MovementEntity movementEntity) {
        return new ReportResponse(
                movementEntity.getDate().toLocalDateTime().toLocalDate(),
                movementEntity.getAccountEntity().getClientName(),
                movementEntity.getAccountEntity().getNumber(),
                movementEntity.getAccountEntity().getType(),
                movementEntity.getInitialBalance(),
                movementEntity.getAccountEntity().isStatus(),
                movementEntity.getAmount(),
                movementEntity.getInitialBalance() + movementEntity.getAmount());
    }

}
