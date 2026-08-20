package com.example.vibecoding.controller;

import com.example.vibecoding.dto.CashFlowResponseDTO;
import com.example.vibecoding.dto.ExpenseRequestDTO;
import com.example.vibecoding.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/expenses")
    public ResponseEntity<CashFlowResponseDTO> createExpense(@RequestBody ExpenseRequestDTO dto) {
        CashFlowResponseDTO created = expenseService.createExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
