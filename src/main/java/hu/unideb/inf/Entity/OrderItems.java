package hu.unideb.inf.Entity;

import javax.persistence.*;

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long OrderItemsId;

    private long lineItemId;

    @ManyToOne
    private Product product;
    private String chosenSize;
    private int quantity;
    @ManyToOne
    private Orders orders;

    public OrderItems() {
    }

    public OrderItems(Product product, String chosenSize, int quantity) {
        this.product = product;
        this.chosenSize = chosenSize;
        this.quantity = quantity;
    }

    public long getLineItemId() {
        return lineItemId;
    }

    public Product getProduct() {
        return product;
    }

    public String getChosenSize() {
        return chosenSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setLineItemId(long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setChosenSize(String chosenSize) {
        this.chosenSize = chosenSize;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
