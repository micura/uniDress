package hu.unideb.inf.Controller;

import hu.unideb.inf.Entity.*;
import hu.unideb.inf.Service.CategoryService;
import hu.unideb.inf.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setServices(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("categories", categoryService.getCategories());
        return "products";
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model) {
        Optional product  = productService.getProduct(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("sizes", productService.getSizesByProductId(id));
        } else {
            System.out.printf("No Product found with id %d%n", id);
        }
        return "product";
    }
}