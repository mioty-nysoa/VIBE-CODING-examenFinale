package com.example.vibecoding.controller;

import com.example.vibecoding.dto.BalanceResponseDTO;
import com.example.vibecoding.service.CashFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final CashFlowService cashFlowService;

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance() {
        return ResponseEntity.ok(cashFlowService.getBalance());
    }
}
