package com.example.demo.config.oauth2;

import com.example.demo.user.UserRepository;
import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("서비스 코드 실행");

        // OAuth2 로그인 실행
        // 소셜 로그인한 후, 우리 데이터베이스 확인 전 (소셜 로그인 한 내용을 변수에 담음)
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 소셜 로그인한 내용을 key, value 형태로 변환해 줌
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 카카오가 사용자에게 부여한 식별번호를 문자 형태로 저장
        String providerId = ((Long)attributes.get("id")).toString();
        System.out.println(attributes);
        System.out.println(providerId);

        // 카카오로그인한 사용자의 식별번호를 이메일로 만들기
        String email = providerId + "@kakao.social";
        Map properties = (Map) attributes.get("properties");
        String name = (String) properties.get("nickname");

        // DB에 회원이 있나 없나 확인
        Optional<User> result = userRepository.findByEmail(email);

        // 없으면 가입 시켜주기
        User user = null;
        if(!result.isPresent()){
            user = userRepository.save(
                    User.builder()
                            .email(email)
                            .name(name)
                            .password("kakao")
                            .enable(true)
                            .role("ROLE_USER")
                            .build()
            );
            // 암호에 kakao라고 넣는 이유는 로그인 요청할 때 패스워드를 암호화한 상태로 db에 조회하기 때문에 조회될 가능성이 없다.

            return AuthUserDetails.from(user);
        }else {
            user = result.get();

            return AuthUserDetails.from(user);
        }
    }
}
