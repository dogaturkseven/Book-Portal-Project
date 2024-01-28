package tr.com.obss.jip2022.bookportalproject.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import tr.com.obss.jip2022.bookportalproject.exception.UserNotFoundException;
import tr.com.obss.jip2022.bookportalproject.model.Role;
import tr.com.obss.jip2022.bookportalproject.model.RoleType;
import tr.com.obss.jip2022.bookportalproject.model.User;
import tr.com.obss.jip2022.bookportalproject.service.RoleService;
import tr.com.obss.jip2022.bookportalproject.service.UserService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class AppInitConfig {

    private final RoleService roleService;
    @Autowired
    private UserService userService;

    public AppInitConfig(@Lazy UserService userService, RoleService roleService)
    {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Bean
    public CommandLineRunner initRoles(){
        return args -> {
            final List<RoleType> allRoles = roleService.getAllRoles().stream().map(Role::getName).toList();
            Arrays.stream(RoleType.values()).filter(roleType -> !allRoles.contains(roleType)).forEach(roleType -> {
                Role role = new Role();
                role.setName(roleType);
                roleService.createNewRole(role);
            });

            try{
                final User adminUser = userService.findByUsername("sys.admin");
            }catch(UserNotFoundException e){
                User adminUser = new User();

                adminUser.setName("System");
                adminUser.setSurname("Admin");
                adminUser.setUsername("sys.admin");
                adminUser.setPassword("admin123");
                adminUser.setRoles(roleService.getAllRoles());
                userService.createNewUser(adminUser);

            }
        };
    }
}