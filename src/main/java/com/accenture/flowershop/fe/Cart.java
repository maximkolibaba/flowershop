package com.accenture.flowershop.fe;

import com.accenture.flowershop.be.entity.flower.Flower;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    private BigDecimal total;

    public Cart() {
        this.items = new ArrayList<CartItem>();
        this.total = BigDecimal.ZERO;
//        this.total = total.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public Cart(Cart cart) {
        if (cart != null) {
            this.items = cart.getItems();
            this.total = cart.getTotal();
//            this.total = total.setScale(2,BigDecimal.ROUND_HALF_UP);
        } else {
//            new Cart();
            //this.items = new ArrayList<CartItem>();
            //this.total = BigDecimal.ZERO;
        }
    }

    public boolean addToCart(CartItem item) {
        int index = items.indexOf(item);
        if (index == -1) {
            items.add(item);
            setTotal();
            return true;
        }
        if (items.get(index).merge(item)) {
            setTotal();
        }
        return false;
    }

    public boolean removeFromCart(CartItem item) {
        if (items.remove(item)) {
            setTotal();
            return true;
        }
        return false;
    }

    public BigDecimal getTotal() {
        return total;
    }

    private void setTotal() {
        total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.getTotal());
        }
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
