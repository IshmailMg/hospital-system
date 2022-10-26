package za.ac.cput.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encoder(){
        //Encodes our passwords for a more secure login system
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService (BCryptPasswordEncoder bCryptPasswordEncoder){
        //Creating the roles for the system
        InMemoryUserDetailsManager man = new InMemoryUserDetailsManager();
        man.createUser(User.withUsername("admin-user")
                .password(bCryptPasswordEncoder.encode("65ff7492d30"))
                .roles("ADMIN")
                .build()
                );

        man.createUser(User.withUsername("client-user")
                .password(bCryptPasswordEncoder.encode("1253208465b"))
                .roles("USER")
                .build()
        );
        return man;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        //Allocating the roles permissions
        httpSecurity.httpBasic()
                .and().csrf().disable().formLogin().disable()
                //Place all roles for different domains below

                //Path matcher For the Hospital System
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/**/hospitalroom/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/hospitalroom/delete/{id}").hasRole("ADMIN")
                //changed this because only the admin should read specific IDs
                .antMatchers(HttpMethod.GET, "/**/hospitalroom/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/hospitalroom/find-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for Doctor
                .antMatchers(HttpMethod.POST, "/hospital-system/doctor/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hospital-system/doctor/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hospital-system/doctor/get-all").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/hospital-system/doctor/delete/{id}").hasRole("ADMIN")

                //Path matcher for the Driver System
                .antMatchers(HttpMethod.POST, "/**/secretary/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/secretary/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/secretary/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/secretary/get-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for cleaning staff
                .antMatchers(HttpMethod.POST, "/hospital-system/cleaningStaff/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hospital-system/cleaningStaff/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hospital-system/cleaningStaff/find-All").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/hospital-system/cleaningStaff/delete/{id}").hasRole("ADMIN")

                //Path matcher for appointment
                .antMatchers(HttpMethod.POST, "/hospital-system/appointment/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hospital-system/appointment/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/hospital-system/appointment/find-all").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/hospital-system/appointment/delete/{id}").hasRole("ADMIN")

                //Path matcher for the Nurse System
                .antMatchers(HttpMethod.POST, "/**/nurse/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/nurse/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/nurse/read/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/**/nurse/find-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for the Driver System
                .antMatchers(HttpMethod.POST, "/**/driver/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/driver/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/driver/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/driver/get-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for the Supplier System
                .antMatchers(HttpMethod.POST, "/**/supplier/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/supplier/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/supplier/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/supplier/find-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for the Invoice System
                .antMatchers(HttpMethod.POST, "/**/invoice/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/invoice/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/invoice/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/invoice/find-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for the Medical Aid System
                .antMatchers(HttpMethod.POST, "/**/medicalaid/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/medicalaid/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/medicalaid/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/medicalaid/find-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for the Medicine System
                .antMatchers(HttpMethod.POST, "/**/medicine/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/medicine/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/medicine/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/medicine/find-all").hasAnyRole("USER", "ADMIN")

                //Path matcher for the Patient System
                .antMatchers(HttpMethod.POST, "/**/patient/save").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**/patient/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/patient/read/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/patient/find-all").hasAnyRole("USER", "ADMIN")




                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
