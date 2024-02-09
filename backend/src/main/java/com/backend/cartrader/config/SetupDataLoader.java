package com.backend.cartrader.config;

import com.backend.cartrader.model.ERole;
import com.backend.cartrader.model.Role;
import com.backend.cartrader.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

        RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!roleRepository.existsByName(ERole.ROLE_USER)) {
            Role role = new Role();
            role.setName(ERole.ROLE_USER);
            roleRepository.save(role);
        }
        if (!roleRepository.existsByName(ERole.ROLE_ADMIN)) {
            Role role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);
        }

    }
}
