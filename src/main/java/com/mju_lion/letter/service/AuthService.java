package com.mju_lion.letter.service;

import com.mju_lion.letter.authentication.PasswordHashEncryption;
import com.mju_lion.letter.dto.request.auth.LoginDto;
import com.mju_lion.letter.dto.request.auth.SigninDto;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.exception.errorcode.ErrorCode;
import com.mju_lion.letter.exception.ConflictException;
import com.mju_lion.letter.exception.NotFoundException;
import com.mju_lion.letter.exception.UnauthorizedException;
import com.mju_lion.letter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordHashEncryption passwordHashEncryption;
    private final UserRepository userRepository;

    //회원가입
    public void signup(SigninDto signinDto){
        String plainPassword= signinDto.getPassword();
        String hashedPassword=passwordHashEncryption.encrypt(plainPassword);

        User user=userRepository.findByUserId(signinDto.getUserId());
        if(null!=user){
            throw new ConflictException(ErrorCode.USERID_ALREADY_EXISTS);
        }

        //유저 만들어주기
        User newUser = User.builder()
                .userId(signinDto.getUserId())
                .password(hashedPassword)   //암호화 된 Password
                .email(signinDto.getEmail())
                .name(signinDto.getName())
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
            throw new UnauthorizedException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
    }
}
