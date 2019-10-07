package by.epam.touragency.config;

import by.epam.touragency.entity.Role;
import by.epam.touragency.logic.UserDetailsServiceImpl;
import by.epam.touragency.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
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
                .authorizeRequests()
//                .antMatchers("/home", "/index.jsp", "/to_about_company", "/to_tours", "/to_tickets", "/back_to_main",
//        "/to_registration", "/to_tour_overview", "/to_inf", "/logout", "/login").permitAll()
//                .antMatchers("change_email", "/change_login", "/change_user_name", "/change_password",
//        "/change_phone_number", "/to_orders", "/change_user_surname")
//                .hasAnyRole(Role.ADMIN.toString(), Role.AGENT.toString(), Role.CLIENT.toString())
//                .antMatchers("/to_login", "/registration").hasRole("ANONYMOUS")
//                .antMatchers("/to_user_profile", "/tour_register_command", "/to_tour_registration",
//        "/to_ticket_registration", "/ticket_register_command", "/change_adults_number",
//        "/change_arrival_city", "/change_arrival_country", "/change_arrival_date",
//        "/change_children_number", "/change_departure_city", "/change_departure_date", "/change_hotel",
//        "/change_nutrition", "/change_price",
//        "/change_status", "/change_tour_name").hasRole(Role.ADMIN.toString())
//                .antMatchers("/to_booking", "/remove_order").hasAnyRole(Role.AGENT.toString(),
//        Role.CLIENT.toString())
//                .antMatchers("/pay_order").hasRole(Role.CLIENT.toString())
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/to_login").loginProcessingUrl("/login")
                .usernameParameter(ParameterConstant.PARAM_NAME_LOGIN)
                .passwordParameter(ParameterConstant.PARAM_NAME_PASSWORD)
                .successForwardUrl("/login_setter").failureUrl("/login_setter")
//                .failureHandler(new AuthenticationFailureHandlerImpl())
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home").permitAll();
    }

    @Bean
    public PasswordEncoder bCrypt() {
        return new BCryptPasswordEncoder();
    }
}
