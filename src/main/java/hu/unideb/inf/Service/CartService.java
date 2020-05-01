package hu.unideb.inf.Service;

import hu.unideb.inf.Entity.OrderItems;
import hu.unideb.inf.Entity.Orders;
import hu.unideb.inf.Entity.Product;
import hu.unideb.inf.Entity.User;
import hu.unideb.inf.Repository.OrderItemsRepository;
import hu.unideb.inf.Repository.OrdersRepository;
import hu.unideb.inf.Repository.ProductRepository;
import hu.unideb.inf.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class CartService {
    private ProductRepository productRepo;
    private OrderItemsRepository orderItemsRepo;
    private OrdersRepository ordersRepo;
    private UserRepository userRepo;
    private List<OrderItems> cart = new ArrayList<>();
    private boolean isActive = false;

    @Autowired
    public void setRepos(ProductRepository productRepo, OrderItemsRepository orderItemsRepo, OrdersRepository ordersRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.orderItemsRepo = orderItemsRepo;
        this.ordersRepo = ordersRepo;
        this.userRepo = userRepo;
    }

    public List<OrderItems> getCart() {
        return cart;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getAmount() {
        int amount = 0;
        for (OrderItems orderItems : cart) {
            amount += orderItems.getQuantity() * orderItems.getProduct().getPrice();
        }
        return amount;
    }

    public void addToCart(Long productToCart, String size, int quantity) {
        Optional product = productRepo.findById(productToCart);

        if (product.isPresent()) {
            Product prod = (Product) product.get();
            OrderItems item = new OrderItems(prod, size, quantity);
            cart.add(item);
        }

        this.isActive = true;
    }

    public void deleteItem(long id, String size) {
        OrderItems obj = cart.stream()
                .filter(p -> p.getProduct().getProductId().equals(id))
                .filter(s -> s.getChosenSize().equals(size)).findFirst().get();
        cart.remove(obj);
    }

    public String pay(String username) {
        User user = userRepo.findUserByUserName(username);

        Orders order = new Orders(
            new Date(Calendar.getInstance().getTimeInMillis()),
            getAmount(),
            user,
            cart
        );

        ordersRepo.save(order);

        int counter = 1;
        for (OrderItems orderItems : cart) {
            orderItems.setLineItemId(counter++);
            orderItems.setOrders(order);
            orderItemsRepo.save(orderItems);
        }

        cart.clear();
        this.isActive = false;
        return "success";
    }
}
