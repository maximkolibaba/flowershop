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

    public OrderStatus next() {
        switch (this) {
            case PENDING_PAYMENT:
                return PROCESSING;
            case PROCESSING:
                return SHIPPED;
            case SHIPPED:
                return DELIVERED;
            case DELIVERED:
                return COMPLETED;
            case COMPLETED:
                return COMPLETED;
        }
        return this;
    }

    @Override
    public String toString() {
        return value;
    }
}
