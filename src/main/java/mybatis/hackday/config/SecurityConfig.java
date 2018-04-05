package mybatis.hackday.config;

import mybatis.hackday.service.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/**").authenticated();

        http.csrf().disable();

        http.formLogin()
            .loginProcessingUrl("/guest/login_processing");

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("user/logout_processing"))
            .invalidateHttpSession(true);
    }

}
