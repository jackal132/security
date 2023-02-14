package acc.security.user.dao;

import acc.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findOneByUserId(String userId);
}
