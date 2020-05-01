package hu.unideb.inf.Repository;

import hu.unideb.inf.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findUserByUserName(String userName);
    User findUserByEmail(String email);
}