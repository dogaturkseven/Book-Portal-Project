package tr.com.obss.jip2022.bookportalproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.bookportalproject.model.Role;
import tr.com.obss.jip2022.bookportalproject.model.RoleType;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findRoleByName(RoleType roleType);
}