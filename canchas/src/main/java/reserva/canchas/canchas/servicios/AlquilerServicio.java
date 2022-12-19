package reserva.canchas.canchas.servicios;

import reserva.canchas.canchas.entidades.*;
import reserva.canchas.canchas.repositorios.*;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AlquilerServicio {
    
    @Autowired
    AlquilerRepositorio alquilerRepositorio;

    public List<Alquiler> getAll()
    {
        List<Alquiler> lista = new ArrayList<Alquiler>();
        lista = (ArrayList<Alquiler>) alquilerRepositorio.findAll();
        return lista;
    }

    public Alquiler getById(int id)
    {
        return alquilerRepositorio.findById(id);
    }

    public void save(Alquiler alquiler)
    {
        alquilerRepositorio.save(alquiler);
    }

    public void delete(int id)
    {
        alquilerRepositorio.deleteById(id);
    }

}
