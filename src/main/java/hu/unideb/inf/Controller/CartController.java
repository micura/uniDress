package hu.unideb.inf.Controller;

import hu.unideb.inf.Entity.Product;
import hu.unideb.inf.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CartController {
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/buy")
    public String buy(
            @ModelAttribute("product") Product product,
            @RequestParam("size") String size,
            @RequestParam("qty") int quantity,
            HttpSession session){
        cartService.addToCart(product.getProductId(), size, quantity);
        session.setAttribute("items", cartService.getCart());
        session.setAttribute("amount", cartService.getAmount());
        return "redirect:/cart?success";
    }

    @RequestMapping("/cart")
    public String cart(Model model,  HttpSession session){
        model.addAttribute("items", session.getAttribute("items"));
        model.addAttribute("amount", session.getAttribute("amount"));
        model.addAttribute("isActive", cartService.isActive());
        return "cart";
    }

    @GetMapping("/cart/delete/{id}/{size}")
    public String deleteItem(@PathVariable("id") long id, @PathVariable("size") String size){
        cartService.deleteItem(id, size);
        return "redirect:/cart?delete";
    }

    @PostMapping("/pay")
    public String pay(Principal principal){
        String user = principal.getName();
        cartService.pay(user);
        return "successorder";
    }
}