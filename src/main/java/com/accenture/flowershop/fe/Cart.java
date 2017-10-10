package com.accenture.flowershop.fe;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cart implements Iterable<CartItem> {
    private List<CartItem> items;

    @Getter
    private BigDecimal total;

    public Cart(Cart cart) {
        this.items = cart != null ? cart.items : new ArrayList<CartItem>();
        this.total = cart != null ? cart.total : BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    // TODO: maybe make boolean
    public void addToCart(Map<Long, Integer> flowers) {

    }

    public boolean removeFromCart(CartItem item) {
        if (items.remove(item)) {
            setTotal();
            return true;
        }
        return false;
    }

    public CartItem discardItem(String flowerName) {
        CartItem item = items.stream().filter(it -> it.getFlower().getName().equals(flowerName)).findFirst().get();
        removeFromCart(item);
        return item;
        // TODO: refactor
        // flowerService.returnToStock(item.getFlower(), item.getAmount());
        // req.getSession(false).setAttribute("cart", cart);
    }

    private void setTotal() {
        total = BigDecimal.ZERO;
        for (CartItem item : this) {
            total = total.add(item.getTotal());
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public Iterator<CartItem> iterator() {
        return items.iterator();
    }
}
