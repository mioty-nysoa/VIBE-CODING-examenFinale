package com.example.vibecoding.service.impl;

import com.example.vibecoding.dto.BalanceResponseDTO;
import com.example.vibecoding.dto.CashFlowResponseDTO;
import com.example.vibecoding.mapper.CashFlowMapper;
import com.example.vibecoding.model.CashFlow;
import com.example.vibecoding.repository.CashFlowRepository;
import com.example.vibecoding.service.CashFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashFlowServiceImpl implements CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    @Override
    public List<CashFlowResponseDTO> getAllCashFlows(String type) {
        List<CashFlow> flows = cashFlowRepository.findAll();
        return flows.stream()
                .filter(f -> type == null || f.getType().equalsIgnoreCase(type))
                .map(CashFlowMapper::toDTO)
                .toList();
    }

    @Override
    public List<CashFlowResponseDTO> getCashFlowsByUserId(String userId) {
        return cashFlowRepository.findByUserId(userId).stream()
                .map(CashFlowMapper::toDTO)
                .toList();
    }

    @Override
    public BalanceResponseDTO getBalance() {
        List<CashFlow> flows = cashFlowRepository.findAll();

        BigDecimal totalDonations = flows.stream()
                .filter(f -> "DONATION".equalsIgnoreCase(f.getType()))
                .map(CashFlow::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = flows.stream()
                .filter(f -> "EXPENSE".equalsIgnoreCase(f.getType()))
                .map(CashFlow::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new BalanceResponseDTO(totalDonations, totalExpenses, totalDonations.subtract(totalExpenses));
    }
}
