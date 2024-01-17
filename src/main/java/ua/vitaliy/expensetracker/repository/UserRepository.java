package ua.vitaliy.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vitaliy.expensetracker.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    List<User> findAll();

    Optional<User> findById(Integer id);

    void deleteById(Integer id);

    void delete(User user);

    boolean existsById(Integer id);

    void updateById(Integer id, User user);

    User save(User user);

    long count();

}
