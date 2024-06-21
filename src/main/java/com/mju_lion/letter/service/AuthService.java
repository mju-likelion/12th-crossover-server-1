package com.mju_lion.letter.service;

import com.mju_lion.letter.authentication.JwtTokenProvider;
import com.mju_lion.letter.authentication.PasswordHashEncryption;
import com.mju_lion.letter.dto.request.auth.LoginDto;
import com.mju_lion.letter.dto.request.auth.SigninDto;
import com.mju_lion.letter.dto.request.term.TermsAgreementDto;
import com.mju_lion.letter.dto.response.token.TokenResponseDto;
import com.mju_lion.letter.entity.Term;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.entity.UserTerm;
import com.mju_lion.letter.exception.errorcode.ErrorCode;
import com.mju_lion.letter.exception.ConflictException;
import com.mju_lion.letter.exception.NotFoundException;
import com.mju_lion.letter.exception.UnauthorizedException;
import com.mju_lion.letter.repository.TermRepository;
import com.mju_lion.letter.repository.UserRepository;
import com.mju_lion.letter.repository.UserTermRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordHashEncryption passwordHashEncryption;
    private final UserRepository userRepository;
    private final TermRepository termRepository;
    private final UserTermRepository userTermRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    public void signup(SigninDto signinDto) {
        String plainPassword = signinDto.getPassword();
        String hashedPassword = passwordHashEncryption.encrypt(plainPassword);

        if (userRepository.findByUserId(signinDto.getUserId()).isPresent()) {
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

        // 약관 동의 저장
        for (TermsAgreementDto termsAgreementDto : signinDto.getTermsAgreementList()) {
            Term term = termRepository.findById(UUID.fromString(termsAgreementDto.getTermId()))
                    .orElseThrow(() -> new NotFoundException(ErrorCode.TERM_NOT_FOUND));

            UserTerm userTerm = UserTerm.builder()
                    .user(newUser)
                    .term(term)
                    .agreed(termsAgreementDto.isAgreed())
                    .build();
            userTermRepository.save(userTerm);
        }
    }

    private User validateUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        return user;
    }

    //로그인
    public TokenResponseDto login(LoginDto loginDto) {
        //유저아이디로 찾기
        User user = validateUserByUserId(loginDto.getUserId());

        //비밀번호 확인
        if (!passwordHashEncryption.matches(loginDto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException(ErrorCode.INVALID_PASSWORD);
        }

        String payload = String.valueOf(user.getId());
        String accessToken = jwtTokenProvider.createToken(payload);

        return new TokenResponseDto(accessToken);
    }
}
