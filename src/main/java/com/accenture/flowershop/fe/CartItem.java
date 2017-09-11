package com.accenture.flowershop.fe;

import com.accenture.flowershop.be.entity.flower.Flower;
import lombok.Getter;

import java.math.BigDecimal;

public class CartItem {
    @Getter
    private Flower flower;

    @Getter
    private int amount;

    @Getter
    private BigDecimal total;

    public CartItem(Flower flower, int amount) {
        this.flower = flower;
        this.amount = amount;
        this.total = flower.getPrice().multiply(new BigDecimal(amount));
//        this.total = total.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public String getFlowerName() {
        return flower.getName();
    }

    //public boolean haveAmount(int amount) {
    //    return amount > 0 && (amount) <= flower.getAmount();
    //}

    public boolean merge(CartItem item) {
//        if (haveAmount(item.amount)) {
        if (item.amount > 0 && item.amount <= flower.getAmount()) {
            amount += item.amount;
            total = flower.getPrice().multiply(new BigDecimal(amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o != null && o instanceof CartItem) {
            flag = this.flower.equals(((CartItem) o).flower);
        }
        return flag;
    }
}