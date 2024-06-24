package com.mju_lion.letter.controller;

import com.mju_lion.letter.authentication.AuthenticationExtractor;
import com.mju_lion.letter.authentication.JwtEncoder;
import com.mju_lion.letter.dto.response.token.TokenResponseDto;
import com.mju_lion.letter.repository.UserRepository;
import com.mju_lion.letter.service.AuthService;
import com.mju_lion.letter.dto.request.auth.LoginDto;
import com.mju_lion.letter.dto.response.ResponseDto;
import com.mju_lion.letter.dto.request.auth.SigninDto;
import jakarta.servlet.http.Cookie;
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
    private final UserRepository userRepository;

    //회원가입
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto<Void>> signup(@RequestBody @Valid SigninDto signinDto) {

        authService.signup(signinDto);

        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "회원 가입 완료"), HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) {

        TokenResponseDto tokenResponseDto = authService.login(loginDto);
        String bearerToken = JwtEncoder.encodeJwtToken(tokenResponseDto.getAccessToken());

        ResponseCookie cookie = ResponseCookie.from(AuthenticationExtractor.TOKEN_COOKIE_NAME, bearerToken)
                .maxAge(Duration.ofMillis(1800000))
                .path("/")
                .httpOnly(true)
                .sameSite("None").secure(true)
                .build();
        response.addHeader("set-cookie", cookie.toString());

        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "로그인 완료"), HttpStatus.OK);
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<ResponseDto<Void>> logout(final HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("AccessToken", null) //인코딩, 쿠키이름과 쿠키값을 지정한다
                .maxAge(0) //쿠키의 만료시간
                .path("/")  //모든 경로에서 쿠키 전달 가능
                .build();
        response.addHeader("set-cookie", cookie.toString());//set-cookie로 헤더에 쿠키를 출력함

        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "로그 아웃 완료"), HttpStatus.OK);
    }
}
