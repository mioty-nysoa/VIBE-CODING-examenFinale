package com.example.vibecoding.service;

import com.example.vibecoding.dto.CashFlowResponseDTO;
import com.example.vibecoding.dto.ExpenseRequestDTO;

public interface ExpenseService {
    CashFlowResponseDTO createExpense(ExpenseRequestDTO dto);
}
