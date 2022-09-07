package com.ll.exam.app10.app.user;

import com.ll.exam.app10.app.exception.UserNotFoundException;
import com.ll.exam.app10.app.user.entity.User;
import com.ll.exam.app10.app.user.form.LoginFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("/Users/gimseonghyeon/Desktop/project/temp/app10")
    private String genFileDirPath;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User signup(LoginFormDto loginFormDto) {

        User user = User.builder()
                .loginId(loginFormDto.getLoginId())
                .password(passwordEncoder.encode(loginFormDto.getPassword1()))
                .name(loginFormDto.getName())
                .email(loginFormDto.getEmail())
                .profileImg(loginFormDto.getProfileImg())
                .build();

        return userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> findUser =  userRepository.findById(id);
        if(!(findUser.isPresent())) throw new UserNotFoundException("유저가 없습니다!");
        return findUser.get();
    }
    public User findByLoginId(String loginId) {
        Optional<User> findUser = userRepository.findByloginId(loginId);

        if (findUser.isEmpty()) {
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }

        return findUser.get();
    }

    public String transImgUrl(MultipartFile profileImg) {

        String profileImgRelPath = "member/" + UUID.randomUUID().toString() + ".png";
        File profileImgFile = new File(genFileDirPath + "/" + profileImgRelPath);
        profileImgFile.mkdirs(); // 관련된 폴더가 혹시나 없다면 만들어준다.
        try {
            profileImg.transferTo(profileImgFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return profileImgRelPath;
    }
}
