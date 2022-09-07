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
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
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
    private String loginSuccess(@ModelAttribute LoginFormDto loginFormDto, MultipartFile profileImg){
        String imgUrl =userService.transImgUrl(profileImg);
        /*Form의 변수에 리턴된 Url을 넣어주는 코드*/
        loginFormDto.setProfileImg(imgUrl);
        userService.signup(loginFormDto);



        User user = userService.findByLoginId(loginFormDto.getLoginId());



        return "redirect:/member/profile/"+user.getId();
    }
    @GetMapping("/profile/{userId}")
    private String profile(@PathVariable("userId") Long id,Model model) {
        User userInfo = userService.findById(id);
        model.addAttribute("userInfo", userInfo);
        return "profile";
    }
}
