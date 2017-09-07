package com.accenture.flowershop.fe;

import com.accenture.flowershop.be.entity.flower.Flower;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class CartItem {
    @Getter
    @Setter
    private Flower flower;

    @Getter
    @Setter
    private int amount;

    @Getter
    @Setter
    private BigDecimal total;

    public CartItem(Flower flower, int amount) {
        this.flower = flower;
        this.amount = amount;
        this.total = flower.getPrice().multiply(new BigDecimal(amount));
//        this.total = total.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public boolean haveAmount(int amount) {
        return amount > 0 && (this.amount + amount) <= flower.getAmount();
    }

    public boolean merge(CartItem item) {
        if (haveAmount(item.amount)) {
            this.amount += item.amount;
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
