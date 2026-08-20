package com.example.vibecoding.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Donation extends CashFlow {
    private String comment;

    public Donation() {
        super();
        this.setType("DONNATION");
    }

    public Donation(String id, Instant createdAt, BigDecimal amount, String userId, String comment) {
        super(id, createdAt, amount, userId, "DONNATION");
        this.comment = comment;
    }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}