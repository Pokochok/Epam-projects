package by.epam.touragency.config;

import by.epam.touragency.logic.UserDetailsServiceImpl;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static by.epam.touragency.util.PageMsgConstant.HOME_PAGE_PATH;
import static by.epam.touragency.util.PageMsgConstant.TO_LOGIN_PAGE_PATH;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCrypt());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/img/**", "/uui/**").permitAll()
                .anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedPage("/error")
                .and()
                .formLogin().loginPage("/to_login").loginProcessingUrl("/login")
                .usernameParameter(ParameterConstant.PARAM_NAME_LOGIN)
                .passwordParameter(ParameterConstant.PARAM_NAME_PASSWORD)
                .successForwardUrl("/home")
                .failureUrl("/fail_login")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home").permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/img/**", "/uui/**");
    }

    @Bean
    public PasswordEncoder bCrypt() {
        return new BCryptPasswordEncoder();
    }
}
