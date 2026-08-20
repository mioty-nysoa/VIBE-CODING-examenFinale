package com.example.vibecoding.dto;

import java.math.BigDecimal;

public class ExpenseRequestDTO {
    private String userId;
    private BigDecimal amount;
    private String reason;
    private String frequency; // NONE, MONTHLY, WEEKLY, YEARLY

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
}
