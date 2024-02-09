package com.backend.cartrader.config.security.services;

import com.backend.cartrader.error.ErrorCode;
import com.backend.cartrader.exception.AuthenticationException;
import com.backend.cartrader.model.User;
import com.backend.cartrader.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> user = userRepository.findByEmail(username);
            if (user.isEmpty()){
                throw new AuthenticationException(ErrorCode.NON_EXISTING_EMAIL, "User Not Found with email: " + username);
            }

            return UserDetailsImpl.build(user.get());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
