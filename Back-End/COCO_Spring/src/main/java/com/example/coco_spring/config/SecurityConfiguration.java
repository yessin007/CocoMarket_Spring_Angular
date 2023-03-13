package com.example.coco_spring.config;

import com.example.coco_spring.Auth.OAuth.CustomOAuth2UserService;
import com.example.coco_spring.Auth.OAuth.OAuthLoginSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    private static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
                googleClientRegistration(),
                facebookClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("878289587767-0hvf9qoli08j251djp5huiionkgsudqs.apps.googleusercontent.com")
                .clientSecret("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")
                .scope("openid", "profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://openidconnect.googleapis.com/v1/userinfo")
                .userNameAttributeName("sub")
                .clientName("Google")
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .build();
    }

    private ClientRegistration facebookClientRegistration() {
        return ClientRegistration.withRegistrationId("facebook")
                .clientId("536697251934455")
                .clientSecret("6d83dda4ffc4d173e84601744fff5beb")
                .scope("email", "public_profile")
                .authorizationUri("https://www.facebook.com/v3.3/dialog/oauth")
                .tokenUri("https://graph.facebook.com/v3.3/oauth/access_token")
                .userInfoUri("https://graph.facebook.com/v3.3/me?fields=id,name,email,first_name,last_name")
                .userNameAttributeName("id")
                .clientName("Facebook")
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .build();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .mvcMatchers("/api/v1/auth/**").permitAll() // added this line to permit all requests with path /api/v1/auth/
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("pass")
                .successHandler(databaseLoginSuccessHandler)
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauth2UserService)
                .and()
                .successHandler(oauthLoginSuccessHandler)
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }

    @Autowired
    private CustomOAuth2UserService oauth2UserService;

    @Autowired
    private OAuthLoginSuccessHandler oauthLoginSuccessHandler;

    @Autowired
    private DatabaseLoginSuccessHandler databaseLoginSuccessHandler;

}
