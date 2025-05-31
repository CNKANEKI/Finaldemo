package org.example.finaldemo.usercontrol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解,默认是关闭的
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                //不需要登录
                .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/myuser/**")).permitAll()

                //需要管理权限
                .requestMatchers(new AntPathRequestMatcher("/myuser/**")).hasAuthority("ADMIN")

                //需要登录
                .requestMatchers(new AntPathRequestMatcher("/**")).hasAnyAuthority("ADMIN", "USER")

        );
        http.httpBasic(Customizer.withDefaults()); //默认配置，// 授权过滤器
        //http.formLogin(Customizer.withDefaults()); //默认配置，// 认证过滤器  用websecurity提供的原始登录，用户为user，密码为系统生成的密码，在控制台显示。http://localhost:8087/logout 退出。

        http.formLogin(formLoginSpec -> formLoginSpec
                .loginPage("/login_page")    //这个是在网址上面显示给用户看的登录地址。他要在controller里再映射位一个网页，这样网页才会打开。
                .loginProcessingUrl("/login")  //这个是固定不变的的，是springsecurity的登录地址，是不变的。
                .usernameParameter("user")  //这里是网页表单 的控件的name的值，必须一致。
                .passwordParameter("pwd")   //这里是网页表单控件的密码的name的值，必须一致。
                .permitAll());
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(logout -> logout
                // 配置注销登录请求 URL，默认 /logout
                .logoutUrl("/logout")    //注意：必须禁止csrf，否则不能GET形式请求。
                .logoutSuccessUrl("/home/") );  //退出功能后跳转的位置

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();//不需要自定义编码器，可以直接返回NoOpPasswordEncoder.getInstance()来避免密码加密。

    }

}