package com.example.vibecoding.mapper;

import com.example.vibecoding.dto.CashFlowResponseDTO;
import com.example.vibecoding.model.CashFlow;
import com.example.vibecoding.model.Donation;
import com.example.vibecoding.model.Expense;

public class CashFlowMapper {

    public static CashFlowResponseDTO toDTO(CashFlow entity) {
        CashFlowResponseDTO dto = new CashFlowResponseDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setAmount(entity.getAmount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUserId(entity.getUserId());

        if (entity instanceof Donation donation) {
            dto.setComment(donation.getComment());
        } else if (entity instanceof Expense expense) {
            dto.setReason(expense.getReason());
            dto.setFrequency(expense.getFrequency() != null ? expense.getFrequency().name() : null);
        }
        return dto;
    }
}
