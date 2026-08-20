package com.example.vibecoding.dto;


import java.math.BigDecimal;
import java.time.Instant;

public class CashFlowResponseDTO {

    private String id;
    private String type;       // "DONATION" ou "EXPENSE"
    private BigDecimal amount;
    private Instant createdAt;
    private String userId;

    // Champs spécifiques à Donation (null si type = EXPENSE)
    private String comment;

    // Champs spécifiques à Expense (null si type = DONATION)
    private String reason;
    private String frequency;

    public CashFlowResponseDTO() {
    }

    // Getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
}
