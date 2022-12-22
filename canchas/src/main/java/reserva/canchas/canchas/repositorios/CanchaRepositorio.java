package reserva.canchas.canchas.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import reserva.canchas.canchas.entidades.*;

@Repository
public interface CanchaRepositorio extends CrudRepository<Cancha, Integer> {
    Cancha findById(int id);
}
