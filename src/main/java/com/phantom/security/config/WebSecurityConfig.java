package com.phantom.security.config;

import com.phantom.security.service.impl.CustomUserDetailServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * 安全配置
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/10 16:26
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    private CustomUserDetailServiceImpl customUserDetailServiceImpl;

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private CustomAuthorizationManager customAuthorizationManager;

    @Resource
    private WhitelistUrl whitelistUrl;

    @Resource
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        // 白名单放行
                        .requestMatchers(whitelistUrl.getUrls().toArray(new String[0])).permitAll()
                        // 系统的自动授权，生产还是需要改成自己通过查询数据库的权限来授权的
                        //.requestMatchers("/**").authenticated()
                        // 自定义详细授权
                        .anyRequest().access(customAuthorizationManager)
                )
//                .formLogin(form -> form.loginPage("/login").permitAll())
                .formLogin().disable()
                // 自定义认证过滤器，需要放在在异常处理顾虑器的后面（如果认证都没通过，就不会走到后面的异常转换处理过滤器）
                .addFilterAfter(jwtAuthenticationFilter, ExceptionTranslationFilter.class)
                // 禁止服务端创建 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 认证异常处理
                .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                // 授权异常处理
                .accessDeniedHandler(customAccessDeniedHandler)
//                .httpBasic()
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }


}
