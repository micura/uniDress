package hu.unideb.inf.Repository;

import hu.unideb.inf.Entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    @Query(value = "SELECT * FROM PRODUCT u WHERE u.category_category_id = ?1", nativeQuery = true)
    List<Product> findAllByCategoryId(long id);
}