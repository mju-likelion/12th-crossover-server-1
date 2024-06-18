package com.mju_lion.letter.authentication;

import com.mju_lion.letter.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@RequestScope
public class AuthenticationContext {
    private User principal;
}
