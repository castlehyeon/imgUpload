package com.ll.exam.app10.app.user;

import com.ll.exam.app10.app.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ll.exam.app10.app.user.form.LoginFormDto;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    private String login(Model model) {
        model.addAttribute("loginFormDto", new LoginFormDto());
        return "login_form";
    }
    @PostMapping("/join")
    private String loginSuccess(@ModelAttribute LoginFormDto loginFormDto){
        userService.signup(loginFormDto);

        return "redirect:/member/profile/"+loginFormDto.getId();
    }
    @GetMapping("/profile/{userId}")
    private String profile(@PathVariable("userId") Long id,Model model) {
        Optional<User> userInfo = userService.findById(id);
        model.addAttribute("userInfo", userInfo);
        return "profile";
    }
}
