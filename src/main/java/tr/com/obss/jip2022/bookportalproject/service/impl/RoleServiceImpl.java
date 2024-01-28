package tr.com.obss.jip2022.bookportalproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tr.com.obss.jip2022.bookportalproject.exception.RoleNotFoundException;
import tr.com.obss.jip2022.bookportalproject.model.Role;
import tr.com.obss.jip2022.bookportalproject.model.RoleType;
import tr.com.obss.jip2022.bookportalproject.repository.RoleRepository;
import tr.com.obss.jip2022.bookportalproject.service.RoleService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findByName(RoleType type) {
        return roleRepository.findRoleByName(type).orElseThrow(RoleNotFoundException::new);
    }

    public void createNewRole(Role role)
    {
        roleRepository.save(role);
    }
}

