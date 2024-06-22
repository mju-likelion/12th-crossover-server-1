package com.mju_lion.letter.authentication;

import com.mju_lion.letter.exception.errorcode.ErrorCode;
import com.mju_lion.letter.exception.UnauthorizedException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationExtractor {
    public static final String TOKEN_COOKIE_NAME = "AccessToken";

    public static String extract(final HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    return JwtEncoder.decodeJwtBearerToken(cookie.getValue()); //디코딩
                }
            }
        }
        throw new UnauthorizedException(ErrorCode.COOKIE_NOT_FOUND);
    }
}
