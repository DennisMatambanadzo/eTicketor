package online.epochsolutions.eticketor.api.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static online.epochsolutions.eticketor.models.user.Permission.*;
import static online.epochsolutions.eticketor.models.user.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
public class WebSecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests(authorize ->authorize.requestMatchers("/auth/**").permitAll());


        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("eTicketor/event/**").hasAnyRole(HOST.name(), ADMIN.name())
                .requestMatchers(GET,"eTicketor/event/**").hasAnyAuthority(ADMIN_READ.name(), HOST_READ.name())
                .requestMatchers(POST,"eTicketor/event/**").hasAuthority( HOST_CREATE.name())
                .requestMatchers(PUT,"eTicketor/event/**").hasAuthority( HOST_UPDATE.name())
                .requestMatchers(DELETE,"eTicketor/event/**").hasAnyAuthority(ADMIN_DELETE.name(), HOST_DELETE.name()));

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("eTicketor/ticket/**").hasRole(USER.name())
                .requestMatchers(POST,"eTicketor/ticket/**").hasAuthority(USER_CREATE.name()));


        http.authorizeHttpRequests(authorize ->authorize
                .requestMatchers("admin/**").hasRole(ADMIN.name())
                .requestMatchers(GET, "admin/**").hasAuthority(ADMIN_READ.name()).anyRequest().authenticated());

//        http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET,"/auth/roleHierarchy").hasRole("USER"));
        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

//    @Bean
//    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
//        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//        expressionHandler.setRoleHierarchy(roleHierarchy());
//        return expressionHandler;
//    }
}
