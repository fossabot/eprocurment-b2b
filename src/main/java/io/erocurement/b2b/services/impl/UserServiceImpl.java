package io.erocurement.b2b.services.impl;



import io.erocurement.b2b.models.entity.Role;
import io.erocurement.b2b.models.entity.User;
import io.erocurement.b2b.models.repository.RoleRepository;
import io.erocurement.b2b.models.repository.UserRepository;
import io.erocurement.b2b.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {




    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("Error en el login: no existe el user '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el user '" + username + "' en el sistema!");
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {

        log.info("findByUsername", username);

        return userRepository.findByUsername(username);
    }

    @Override

    public User save(User user) {
        log.info("User", user); // this is only to test services I know that this is a  bad pratice, however I need to test all cloud services
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Role> roles = this.roleRepository.findAll().stream().filter(role -> user.getRoles().contains(role)).collect(Collectors.toList());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword().toString()));
        user.setUsername(user.getUsername().toLowerCase());
        return this.userRepository.save(user);
    }

    @Override
    public Boolean existsByUsername(String username) {

        log.info("User", username); // this is only to test services I know that this is a  bad pratice, however I need to test all cloud services
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String username) {
        log.info("User", username); // this is only to test services I know that this is a  bad pratice, however I need to test all cloud services
        return this.userRepository.existsByEmail(username);
    }

}
