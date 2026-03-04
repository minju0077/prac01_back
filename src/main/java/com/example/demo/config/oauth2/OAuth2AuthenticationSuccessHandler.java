package com.example.demo.config.oauth2;

import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("OAuth2 로그인 성공");

        // OAuth2UserService 에서 확인했던 객체를 보관해서 꺼내는 것
        AuthUserDetails user = (AuthUserDetails)authentication.getPrincipal();

        // 토큰 발급
        String jwt = jwtUtil.createToken(user.getIdx(), user.getUsername(), user.getRole());
        // 발급한 코튼 쿠키에 저장
        response.addHeader("Set-Cookie", "ATOKEN="+ jwt + "; Path=/;");
        // 리다이렉트 주소 저장
        String redirectUrl = "http://localhost:5173/success";
        // 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
