package com.acj.spa.config.security;

import com.acj.spa.config.paths.CorePathBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "restservice";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID);
    }
    private static final String[] WHITELIST = {
            "/swagger-resources/**",
            "/resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/public/**",
            "/system-logout/**"
    };

    @Override
    public void configure(HttpSecurity httpSecurity)throws Exception{
//        httpSecurity.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, CorePathBase.PUBLIC_PATH + "/**").permitAll();
        httpSecurity.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(WHITELIST).permitAll()
                .anyRequest().fullyAuthenticated();

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
        private TokenStore tokenStore = new InMemoryTokenStore();

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private MyUserDetailsService myUserDetailsService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        private static final String WRITE = "write";
        private static final String READ = "read";
        private static final String BAR = "bar";
        private static final String REFRESH_TOKEN = "refresh_token";
        private static final String AUTHORIZATION_CODE = "authorization_code";
        private static final String PASS_WORD = "password";


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

            endpoints
                    .tokenStore(this.tokenStore)
                    .authenticationManager(this.authenticationManager);
        }

        @Override
        public void configure (ClientDetailsServiceConfigurer clients) throws Exception{
            clients
                    .inMemory()
                    .withClient("web")
                    .authorizedGrantTypes(PASS_WORD, AUTHORIZATION_CODE, REFRESH_TOKEN).scopes(BAR, READ, WRITE)
                    .refreshTokenValiditySeconds(2592000)
                    .resourceIds(RESOURCE_ID)
                    .secret(passwordEncoder.encode("123"))
                    .accessTokenValiditySeconds(86400)
                    .and()
                    .withClient("mobile")
                    .authorizedGrantTypes(PASS_WORD, AUTHORIZATION_CODE, REFRESH_TOKEN).scopes(BAR, READ, WRITE)
                    .refreshTokenValiditySeconds(2592000)
                    .resourceIds(RESOURCE_ID)
                    .secret(passwordEncoder.encode("123"))
                    .accessTokenValiditySeconds(86400);
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices(){
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setSupportRefreshToken(true);
            defaultTokenServices.setTokenStore(this.tokenStore);
            return defaultTokenServices;
        }
    }
}
