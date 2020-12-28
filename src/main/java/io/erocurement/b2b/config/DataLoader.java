package io.erocurement.b2b.config;


import io.erocurement.b2b.models.entity.Role;
import io.erocurement.b2b.models.entity.RoleName;
import io.erocurement.b2b.models.entity.User;
import io.erocurement.b2b.models.repository.RoleRepository;
import io.erocurement.b2b.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(ApplicationArguments args) {
        this.createUser();

    }

    private void createUser() {

        User user = new User();
        if (!this.userRepository.existsByUsername("admin@admin.com")) {
            roleRepository.save(Role.builder().name(RoleName.ROLE_USER.name()).build());
            roleRepository.save(Role.builder().name(RoleName.ROLE_ADMIN.name()).build());
            roleRepository.save(Role.builder().name(RoleName.ROLE_MODERATOR.name()).build());
            user.setPassword(passwordEncoder().encode("12345"));
            user.setUsername("admin@admin.com");
            user.setFirtsName("Jhon");
            user.setLastName("Man");
            user.setEmail("jhon.man@domain.com");

            user.setEnabled(true);
            this.roleRepository.findAll().forEach(role -> {
                user.addRole(role);
            });

            this.userRepository.save(user);

        }


    }

}
