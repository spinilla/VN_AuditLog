package com.viewnext.formacion.auditlog;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.viewnext.formacion.auditlog.core.model.UserApp;
import com.viewnext.formacion.auditlog.core.utils.Constantes;
import com.viewnext.formacion.auditlog.core.utils.SecurityUtils;
import com.viewnext.formacion.auditlog.tips.repository.TipCascadeSaveMongoEventListener;
import com.viewnext.formacion.auditlog.tips.repository.UserAppRepository;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories
@EnableMongoAuditing
@SpringBootApplication
public class AuditLogApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuditLogApplication.class, args);
  }

  @Bean
  public TipCascadeSaveMongoEventListener userCascadingMongoEventListener() {
    return new TipCascadeSaveMongoEventListener();
  }

  /** Pre-load the system with employees and items. */
  public @PostConstruct void init() {
    //    listUsers = userRepository.findAll();

    /**
     * Due to method-level protections on {@link com.viewnext.formacion.auditlog.tips.repository.UserAppRepository}, the security
     * context must be loaded with an authentication token containing the necessary privileges.
     */
    SecurityUtils.runAs("system", "system", "ROLE_ADMIN");

    SecurityContextHolder.clearContext();
  }

  /**
   * 
   * 
   * com.viewnext.formacion.auditlog
   * 
   * SecurityConfiguration
   * <p>SecurityConfiguration This application is secured at both the URL level for some parts, and
   * the method level for other parts. The URL security is shown inside this code, while
   * method-level annotations are enabled at by {@link EnableGlobalMethodSecurity}.
   * 
   * @author Sergio Pinilla (Viewnext/IBM)
   * @version 0.1
   * @since 21 ago. 2018
   *
   */
  @Configuration
  @EnableGlobalMethodSecurity(prePostEnabled = true)
  @EnableWebSecurity
  static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired UserAppRepository userRepository;
    /**
     * This section defines the user accounts which can be used for authentication as well as the
     * roles each user has.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

      UserBuilder builder = User.withDefaultPasswordEncoder();

      List<UserApp> listUsers = userRepository.findAll();

      List<UserDetails> userDetails = new ArrayList<>();
      if (listUsers.isEmpty()) {
        UserDetails admin = builder.username(Constantes.CONST_ADMIN_DEFAULT_ID).password("admin").roles("ADMIN").build();
        userDetails.add(admin);
        UserDetails user = builder.username(Constantes.CONST_USER_DEFAULT_ID).password("user").roles("ADMIN").build();
        userDetails.add(user);
      } else {
        for (UserApp userApp : listUsers) {
          UserDetails admin =
              builder
                  .username(userApp.getNick())
                  .password(userApp.getPassword())
                  .roles(userApp.getRole().getValor())
                  .build();
          userDetails.add(admin);
        }
      }
      return new InMemoryUserDetailsManager(userDetails);
    }

    /**
     * This section defines the security policy for the app.
     *
     * <p>
     *
     * <ul>
     *   <li>BASIC authentication is supported.
     *   <li>/tip is secured using URL security shown below.
     *   <li>CSRF headers are disabled since we are only testing the REST interface, not a web one.
     * </ul>
     *
     * NOTE: GET is not shown which defaults to permitted.
     *
     * @param http
     * @throws Exception
     * @see
     *     org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.httpBasic()
          .and()
          .authorizeRequests()
          .antMatchers(HttpMethod.POST, "/tip")
          .hasRole("ADMIN")
          .antMatchers(HttpMethod.PUT, "/tip/**")
          .hasRole("ADMIN")
          .antMatchers(HttpMethod.PATCH, "/tip/**")
          .hasRole("ADMIN")
          .and()
          .csrf()
          .disable();
    }
  }
}
