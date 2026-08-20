package com.example.vibecoding.controller;

import com.example.vibecoding.dto.CashFlowResponseDTO;
import com.example.vibecoding.service.CashFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CashFlowController {

    private final CashFlowService cashFlowService;

    @GetMapping("/cash-flows")
    public ResponseEntity<List<CashFlowResponseDTO>> getAllCashFlows(
            @RequestParam(required = false) String type) {
        return ResponseEntity.ok(cashFlowService.getAllCashFlows(type));
    }
}
