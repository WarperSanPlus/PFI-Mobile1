package com.example.pfi.Abstract;

import java.util.ArrayList;

public abstract class Panier<T extends Product> {
    private final ArrayList<PanierItem> items = new ArrayList<>();

    // region Add item
    public void addItem(T item) { addItem(item, 1); }

    public void addItem(T item, int amount) {
        // Try to get the PanierItem associated with 'item'
        PanierItem panierItem = getItem(item);

        // If not found
        if (panierItem == null)
        {
            // Create a new PanierItem
            addPanierItem(item, amount);
        }
        else {

            if (panierItem.getAmount() + amount > item.getStockAmount())
                return;

            // Add amount to the PanierItem
            panierItem.addAmount(amount);

            // If the PanierItem has no more items in it
            if (panierItem.getAmount() <= 0) {
                items.remove(panierItem);
            }

        }
        onUpdate();
    }
    // endregion
    // region Get item informations

    private boolean hasItem(T item) { return getItem(item) != null; }

    private PanierItem getItem(T item) {
        for (PanierItem i : items) {
            if (i.item == item)
                return i;
        }
        return null;
    }

    public int getItemAmount(T item) {
        PanierItem panierItem = getItem(item);
        return panierItem == null ? 0 : panierItem.getAmount();
    }

    // endregion
    // region Panier Item

    private void addPanierItem(T item, int amount) {
        if (amount <= 0 || item.getStockAmount() < amount + getItemAmount(item))
            return;

        items.add(new PanierItem(item, amount));
    }

    protected ArrayList<T> getItems() {
        ArrayList<T> litItems = new ArrayList<>();

        for (PanierItem pi : items) {
            litItems.add(pi.item);
        }
        return litItems;
    }

    private class PanierItem {
        // region Amount

        private int amount;

        public int getAmount() { return amount; }

        public void addAmount(int amount) { this.amount += amount; }

        // endregion
        public T item;

        public PanierItem(T item, int initialAmount) {
            this.item = item;
            this.amount = initialAmount;
        }
    }

    // endregion
    // region Update Listener
    private void onUpdate() {
        for (Runnable c : onUpdateListeners) {
            if (c != null) {
                try {
                    c.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private final ArrayList<Runnable> onUpdateListeners = new ArrayList<>();
    public void addOnUpdateListener(Runnable callable) {
        onUpdateListeners.add(callable);
    }
    // endregion
}
