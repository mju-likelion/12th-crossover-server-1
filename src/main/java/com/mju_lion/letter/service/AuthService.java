package com.mju_lion.letter.service;

import com.mju_lion.letter.authentication.PasswordHashEncryption;
import com.mju_lion.letter.dto.request.auth.LoginDto;
import com.mju_lion.letter.dto.request.auth.SignupDto;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.error.ErrorCode;
import com.mju_lion.letter.error.exception.ConflictException;
import com.mju_lion.letter.error.exception.ForbiddenException;
import com.mju_lion.letter.error.exception.NotFoundException;
import com.mju_lion.letter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordHashEncryption passwordHashEncryption;
    private final UserRepository userRepository;

    //회원가입
    public void signup(SignupDto signupDto){
        String plainPassword=signupDto.getPassword();
        String hashedPassword=passwordHashEncryption.encrypt(plainPassword);

        User user=userRepository.findByUserId(signupDto.getUserId());
        if(null!=user){
            throw new ConflictException(ErrorCode.USERID_ALREADY_EXISTS);
        }

        //유저 만들어주기
        User newUser = User.builder()
                .userId(signupDto.getUserId())
                .password(hashedPassword)   //암호화 된 Password
                .email(signupDto.getEmail())
                .name(signupDto.getName())
                .build();
        userRepository.save(newUser);
    }

    //로그인
    public void login(LoginDto loginDto){
        //유저아이디로 찾기
        User user=userRepository.findByUserId(loginDto.getUserId());
        if(null==user){
            throw new NotFoundException(ErrorCode.USERID_NOT_FOUND);
        }

        //비밀번호 확인
        if(!passwordHashEncryption.matches(loginDto.getPassword(), user.getPassword())){
            throw new ForbiddenException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
    }
}
