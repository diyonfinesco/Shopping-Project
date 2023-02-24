package com.example.demo.dto.order;

import jakarta.validation.constraints.NotNull;

public class UpdateOrderDTO {
    @NotNull
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
