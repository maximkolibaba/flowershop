package com.accenture.flowershop.be.entity.order;

public enum OrderStatus {
    PENDING_PAYMENT("Pending Payment"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    COMPLETED("Completed");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
