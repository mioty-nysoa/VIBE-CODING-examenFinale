package com.example.vibecoding.service.impl;

import com.example.vibecoding.dto.CashFlowResponseDTO;
import com.example.vibecoding.dto.ExpenseRequestDTO;
import com.example.vibecoding.mapper.CashFlowMapper;
import com.example.vibecoding.model.Expense;
import com.example.vibecoding.repository.CashFlowRepository;
import com.example.vibecoding.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final CashFlowRepository cashFlowRepository;

    @Override
    public CashFlowResponseDTO createExpense(ExpenseRequestDTO dto) {
        Expense expense = CashFlowMapper.toExpenseEntity(dto);
        Expense saved = cashFlowRepository.save(expense);
        return CashFlowMapper.toDTO(saved);
    }
}
