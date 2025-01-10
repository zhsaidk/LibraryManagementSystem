package com.zhsaidk.config;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.Entity.Role;
import com.zhsaidk.database.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@Configuration

@RequiredArgsConstructor
public class SecurityConfiguration {
    private final PersonService personService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/registration", "/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/persons", "/persons/{id}", "/persons/{id}/delete", "/admin/**", "/assign", "/unassign").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/persons"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .oauth2Login(loginPage->loginPage
                        .loginPage("/login")
                        .defaultSuccessUrl("/persons")
                        .userInfoEndpoint(userInfo->userInfo
                                .oidcUserService(oidcUserService())))
                .build();
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService(){
        return userRequest -> {
            String email = userRequest.getIdToken().getClaim("email");
            UserDetails userDetails = personService.loadUserByUsername(email);

            //todo create User!
            Set<Method> methods = Set.of(UserDetails.class.getMethods());
            DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), userRequest.getIdToken());
            return (OidcUser) Proxy.newProxyInstance(SecurityConfigurer.class.getClassLoader(),
                    new Class[]{UserDetails.class, OidcUser.class},
                    (proxy, method, args) -> methods.contains(method)
                                            ? method.invoke(userDetails, args)
                                            : method.invoke(oidcUser, args));

        };
    }
}
