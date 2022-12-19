package reserva.canchas.canchas.servicios;

import reserva.canchas.canchas.entidades.*;
import reserva.canchas.canchas.repositorios.*;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CanchaServicio {
    
    @Autowired
    CanchaRepositorio canchaRepositorio;

    public List<Cancha> getAll()
    {
        List<Cancha> lista = new ArrayList<Cancha>();
        canchaRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Cancha getById(int id)
    {
        return canchaRepositorio.findById(id);
    }

    public void save(Cancha campo)
    {
        canchaRepositorio.save(campo); 
    }

    public void delete(int id)
    {
        canchaRepositorio.deleteById(id);
    }
}
