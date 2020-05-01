package hu.unideb.inf.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long orderId;

    private Date orderDate;
    private int orderTotalPrice;
    @ManyToOne
    private User customer;
    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItems;

    public Orders() {
    }

    public Orders(Date orderDate, int orderTotalPrice, User customer, List<OrderItems> orderItems) {
        this.orderDate = orderDate;
        this.orderTotalPrice = orderTotalPrice;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public long getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}