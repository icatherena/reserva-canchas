package reserva.canchas.canchas.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.repository.CrudRepository;
=======
//import org.springframework.data.repository.CrudRepository;
>>>>>>> origin/kt
import org.springframework.stereotype.Repository;

import reserva.canchas.canchas.entidades.*;

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler, Integer> {
    Alquiler findById(int id);
}
