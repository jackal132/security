package acc.security.user.dao;

import acc.security.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByRoleId(Long roleId);
}
