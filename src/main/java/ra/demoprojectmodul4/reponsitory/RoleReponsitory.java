package ra.demoprojectmodul4.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.demoprojectmodul4.model.Role;

import java.util.List;

@Repository
public interface RoleReponsitory extends JpaRepository<Role , Integer> {
    Role findRolesByName(String name);
}
