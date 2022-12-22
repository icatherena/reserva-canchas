package reserva.canchas.canchas.servicios;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import reserva.canchas.canchas.entidades.Usuario;
import reserva.canchas.canchas.entidades.Rol;
import reserva.canchas.canchas.repositorios.*;

@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder codificador; 

/*    public void registrarUsuario(Usuario user) {
        usuarioRepositorio.save(user);
    }

    //TODO: Checkear si el ID buscado por nombre de usuario se correlaciona con el ID de la base de datos
    public boolean authenticate(String nombreUsuario, String contraseña) {
        Usuario user = usuarioRepositorio.findById(nombreUsuario);
        return user != null && user.authenticate(nombreUsuario, contraseña);
    } */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        List<GrantedAuthority> ga = buildAuthorities(usuario.getRol());
        return buildUser(usuario, ga);
    }

    public User buildUser(Usuario usuario, List<GrantedAuthority> ga) {
        return new User(usuario.getEmail(), usuario.getContraseña(), ga);
    }

    public List<GrantedAuthority> buildAuthorities(Rol rol) {
        List<GrantedAuthority> ga = new ArrayList<>();
        ga.add( new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        return ga;
    }

    @Transactional
    public void registro(Usuario usuario) {
        if (usuarioRepositorio.existsByEmail(usuario.getEmail()))
            throw new IllegalArgumentException("El correo electrónico ingresado ya está en uso");

        usuario.setContraseña( codificador.encode(usuario.getContraseña()) );
        usuario.setRol(rolRepositorio.findByNombre("Usuario").orElseThrow(() -> new IllegalArgumentException("Error al crear usuario")));
        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> getAll()
    {
        List<Usuario> lista = new ArrayList<Usuario>();
        lista = (ArrayList<Usuario>) usuarioRepositorio.findAll();
        return lista;
    }
}
