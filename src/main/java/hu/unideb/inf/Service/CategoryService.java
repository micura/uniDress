package hu.unideb.inf.Service;

import hu.unideb.inf.Entity.Category;
import hu.unideb.inf.Entity.Product;
import hu.unideb.inf.Repository.CategoryRepository;
import hu.unideb.inf.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;
    private ProductRepository productRepo;

    @Autowired
    public void setRepos(CategoryRepository categoryRepo, ProductRepository productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    public List<Product> getProducts(Long categoryId) {
        return productRepo.findAllByCategoryId(categoryId);
    }
}
