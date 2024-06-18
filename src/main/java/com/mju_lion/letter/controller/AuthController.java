package com.mju_lion.letter.controller;

import com.mju_lion.letter.authentication.JwtTokenProvider;
import com.mju_lion.letter.service.AuthService;
import com.mju_lion.letter.dto.LoginDto;
import com.mju_lion.letter.dto.ResponseDto;
import com.mju_lion.letter.dto.SignupDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto<Void>> signup(@RequestBody @Valid SignupDto signupDto, HttpServletResponse response){

        authService.signup(signupDto);

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.CREATED,
                "회원 가입 완료"
        ), HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>>login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response){
        authService.login(loginDto);

        String payload = loginDto.getUserId();
        String accessToken = jwtTokenProvider.createToken(payload);

        ResponseCookie cookie = ResponseCookie.from("AccessToken", "Bearer+" + accessToken)
                .maxAge(Duration.ofMillis(1800000))
                .path("/")
                .build();
        response.addHeader("set-cookie", cookie.toString());

        return new ResponseEntity<>(ResponseDto.res(
                HttpStatus.OK,
                "로그인 완료:"
        ), HttpStatus.OK);
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
