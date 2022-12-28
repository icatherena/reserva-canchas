package reserva.canchas.canchas.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import reserva.canchas.canchas.servicios.UsuarioServicio;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests()
        .antMatchers("/css/*", "/js/*", "/fonts/*", "/images/*", "/images/*/*", "/", "/registro")
        .permitAll()
        .and()
        .authorizeHttpRequests().antMatchers( "/alquileres/crear")
        .hasAnyRole("Administrador", "Usuario")
        .and()
        .authorizeHttpRequests().antMatchers("/alquileres","/alquileres/crear", "/alquileres/editar/*", "/alquileres/ver",
        "/complejosdeportivos", "/complejosdeportivos/ver", "/complejosdeportivos/editar/*", "/deportes", "/deportes/ver", "/deportes/editar/*",
        "/canchas", "/canchas/ver", "/canchas/editar/*", "/roles", "/roles/ver", "/roles/editar/*")
        .hasRole("Administrador")
        .and()
        .authorizeHttpRequests().antMatchers("/alquileres/editar/*")
        .hasRole("Usuario")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
        .usernameParameter("email").passwordParameter("contraseña")
        .defaultSuccessUrl("/loginSuccess").permitAll()
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .permitAll();
        
        return http.build();
    }
    
}