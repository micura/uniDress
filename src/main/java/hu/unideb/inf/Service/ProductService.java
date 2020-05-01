package hu.unideb.inf.Service;

import hu.unideb.inf.Entity.Category;
import hu.unideb.inf.Entity.OrderItems;
import hu.unideb.inf.Entity.Product;
import hu.unideb.inf.Entity.Size;
import hu.unideb.inf.Repository.ProductRepository;
import hu.unideb.inf.Repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private SizeRepository sizeRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProduct(long id) {
        return productRepo.findById(id);
    }

    public List<Size> getSizesByProductId(long productId) {
        return sizeRepo.findSizesByProductId(productId);
    }
}
