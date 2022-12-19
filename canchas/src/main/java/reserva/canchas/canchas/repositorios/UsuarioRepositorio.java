package reserva.canchas.canchas.repositorios;
import org.springframework.data.repository.CrudRepository;

import reserva.canchas.canchas.entidades.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
    
}

