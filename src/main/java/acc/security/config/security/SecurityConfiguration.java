package acc.security.config.security;

import acc.security.user.enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    private final String WHITELIST[] = {
            "/",
            "/home",
            "/signUp"
    };

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(WHITELIST).permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
                    .antMatchers("/system").hasRole(UserRole.SYSTEM.toString()) // SYSTEM 역할을 가지고 있어야 접근
                    .anyRequest().authenticated() // 그외 모든 리소스를 의미하며 인증 필요
                    .and()
                .formLogin()
                    .loginPage("/login") // 기본 로그인 페이지
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    // .logoutUrl("/logout") // 로그아웃 url (기본 값 : /logout)
                    // .logoutSuccessUrl("/login?logout") // 로그아웃 성공 url (기본값 : /login?logout)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                    .deleteCookies("JSESSIONID") //로그아웃시 JSESSIONID 제거
                    .invalidateHttpSession(true) // 로그아웃시 세션 종료
                    .clearAuthentication(true) // 로그아웃시 권한 제거
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/accessDenied");

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
