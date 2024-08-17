package practice.board.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration  //스프링의 설정 클래스임을 나타냄.
@EnableWebSecurity  //스프링 security 웹 보안기능 활성화
public class SecurityConfig {

    // 비밀번호를 암호화하기 위해 사용하는 PasswordEncoder
    //BCrypt는 비밀번호를 암호화 하는 알고리즘.
    // 사용자 비밀번호를 저장하기 전 암호화, 로그인시 입력한 비밀번호ㅎ와 디비에 저장된 해시된 비밀번호 비교
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    //securityfilterchain http 보안 설정 함수
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(cors -> cors.disable())  // CORS 방지 설정 (기본값 사용) - cors? -> 브라우저가 서로 다른 도메인 에서의 리소스 요청 허용하는 정책
                                            // cors.disable로 모든 cors요청 차단.
                .csrf(csrf -> csrf.disable())  // CSRF 방지 설정 비활성화 - csrf? -> 사용자의 인증 정보를 도용하여 악의적 요청을 서버에 보내는 공격 csrf 보호 비활성화하여
                                            // 요청이 인증되지 않아도 처리 가능.
                .formLogin(formLogin -> formLogin.disable())  // 기본 로그인 페이지 비활성화
                                                                // spring security는 기본 로그인 페이지 제공
                                                                // 해당 설정을 통해 기본 로그인 페이지를 사용하지 않고 커스텀 로그인 페이지 사용.
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable())  // 프레임 옵션 비활성화
                );

        return http.build();
    }
}
