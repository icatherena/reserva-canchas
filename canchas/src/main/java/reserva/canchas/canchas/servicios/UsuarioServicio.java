package reserva.canchas.canchas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reserva.canchas.canchas.entidades.Usuario;
import reserva.canchas.canchas.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void registrarUsuario(Usuario user) {
        usuarioRepositorio.save(user);
    }

    //TODO: Checkear si el ID buscado por nombre de usuario se correlaciona con el ID de la base de datos
    public boolean authenticate(String nombreUsuario, String contraseña) {
        Usuario user = usuarioRepositorio.findById(nombreUsuario);
        return user != null && user.authenticate(nombreUsuario, contraseña);
    }
}
