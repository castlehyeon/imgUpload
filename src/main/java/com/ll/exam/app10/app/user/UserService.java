package com.ll.exam.app10.app.user;

import com.ll.exam.app10.app.user.entity.User;
import com.ll.exam.app10.app.user.form.LoginFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User signup(LoginFormDto loginFormDto) {
        User user = User.builder()
                .loginId(loginFormDto.getLoginId())
                .password(passwordEncoder.encode(loginFormDto.getPassword1()))
                .name(loginFormDto.getName())
                .build();

        return userRepository.save(user);
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
