package hu.unideb.inf.Service;

import hu.unideb.inf.Entity.Orders;
import hu.unideb.inf.Entity.User;
import hu.unideb.inf.Repository.OrdersRepository;
import hu.unideb.inf.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private UserRepository userRepo;

    public List<Orders> findOrdersByUserName(String userName){
        User user = userRepo.findUserByUserName(userName);
        return ordersRepo.findOrdersByUserId(user.getCustomerId());
    }
}
