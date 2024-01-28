package tr.com.obss.jip2022.bookportalproject.service;

import tr.com.obss.jip2022.bookportalproject.model.Role;
import tr.com.obss.jip2022.bookportalproject.model.RoleType;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findByName(RoleType type);

    void createNewRole(Role role);
}
