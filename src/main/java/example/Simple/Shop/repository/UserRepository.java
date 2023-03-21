package example.Simple.Shop.repository;

import example.Simple.Shop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long userId);
}
