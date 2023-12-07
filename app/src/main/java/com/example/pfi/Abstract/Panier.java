package com.example.pfi.Abstract;

import java.util.ArrayList;

public abstract class Panier<T extends Product> {
    private final ArrayList<PanierItem> items = new ArrayList<>();

    // region Add item
    /**
     * Add the given amount of the given item to this Panier.
     */
    public void addItem(T item, int amount) {
        // Try to get the PanierItem associated with 'item'
        PanierItem panierItem = getItem(item);

        // If not found
        if (panierItem == null)
        {
            // Create a new PanierItem
            createPanierItem(item, amount);
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
    // region Remove item
    /**
     * Remove all items of this Panier
     */
    public void clear() {
        items.clear();
        onUpdate();
    }

    public void consume() {
        for (PanierItem i : items) {
            onConsume(i.item, i.getAmount());
        }
        clear();
    }

    protected void onConsume(T item, int amount) { }

    // endregion
    // region Get item informations
    /**
     * @return This panier has the given item in it
     */
    public boolean hasItem(T item) { return getItem(item) != null; }

    /**
     * @return The PanierItem associated with the given item. If not associated, return null
     */
    private PanierItem getItem(T item) {
        for (PanierItem i : items) {
            if (i.item != null && i.item.compareTo(item) == 0)
                return i;
        }
        return null;
    }

    /**
     * @return Amount of the given item in this Panier
     */
    public int getItemAmount(T item) {
        PanierItem panierItem = getItem(item);
        return panierItem == null ? 0 : panierItem.getAmount();
    }

    /**
     * @return Amount of different items in this Panier
     */
    public int getItemCount() {
        return items.size();
    }
    // endregion
    // region Panier Item
    /**
     * Create a PanierItem associated to the given item with the given quantity
     */
    private void createPanierItem(T item, int amount) {
        if (amount <= 0 || item.getStockAmount() < amount + getItemAmount(item))
            return;

        items.add(new PanierItem(item, amount));
    }

    /**
     * @return Items in this Panier
     */
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
        // TODO : REMOVE NOT NECESSARY LISTENERS
        for (Runnable c : onUpdateListeners) {
            try {
                c.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final ArrayList<Runnable> onUpdateListeners = new ArrayList<>();
    public void addOnUpdateListener(Runnable callable) {
        onUpdateListeners.add(callable);
    }
    // endregion
}
