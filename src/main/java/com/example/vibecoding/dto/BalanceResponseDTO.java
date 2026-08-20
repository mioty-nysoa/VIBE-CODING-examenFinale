package com.example.vibecoding.dto;

import java.math.BigDecimal;

public class BalanceResponseDTO {
    private BigDecimal totalDonations;
    private BigDecimal totalExpenses;
    private BigDecimal balance;

    public BalanceResponseDTO(BigDecimal totalDonations, BigDecimal totalExpenses, BigDecimal balance) {
        this.totalDonations = totalDonations;
        this.totalExpenses = totalExpenses;
        this.balance = balance;
    }

    public BigDecimal getTotalDonations() { return totalDonations; }
    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public BigDecimal getBalance() { return balance; }
}
