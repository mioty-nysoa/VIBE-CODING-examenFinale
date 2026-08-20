package com.example.vibecoding.service;



import com.example.vibecoding.dto.CashFlowResponseDTO;
import java.util.List;

public interface CashFlowService {
    List<CashFlowResponseDTO> getAllCashFlows(String type);
}