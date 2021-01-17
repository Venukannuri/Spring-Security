package com.vkannuri.security.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/** @author Venu Kannuri . */
@EnableAuthorizationServer
@Configuration
public class AuthorizationConfiguration implements AuthorizationServerConfigurer {

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .checkTokenAccess("permitAll()")
        .tokenKeyAccess("permitAll()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()
        .withClient("guest_app")
        .scopes("READ_GUEST", "WRITE_GUEST", "UPDATE_GUEST")
        .secret("secret")
        .autoApprove(true)
        .authorities("ROLE_GUEST_AUTHORIZED_CLIENT")
        .authorizedGrantTypes("client_credentials")
        .and()
        .withClient("music_app")
        .scopes("READ_GUEST")
        .secret("secret")
        .autoApprove(true)
        .authorities("ROLE_GUEST_AUTHORIZED_CLIENT")
        .authorizedGrantTypes("client_credentials");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(new InMemoryTokenStore());
  }
}
