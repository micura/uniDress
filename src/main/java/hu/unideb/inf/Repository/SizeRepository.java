package hu.unideb.inf.Repository;

import hu.unideb.inf.Entity.Role;
import hu.unideb.inf.Entity.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends CrudRepository<Size, Long> {
    @Query(value = "SELECT s.* FROM SIZE s inner join product_sizes p on s.size_id=p.size_id where product_id = ?1", nativeQuery = true)
    List<Size> findSizesByProductId(long id);
}