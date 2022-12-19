package reserva.canchas.canchas.servicios;

import reserva.canchas.canchas.entidades.*;
import reserva.canchas.canchas.repositorios.*;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class DeporteServicio {
    
    @Autowired
    DeporteRepositorio deporteRepositorio;

    public List<Deporte> getAll()
    {
        List<Deporte> lista = new ArrayList<Deporte>();
        lista = (ArrayList<Deporte>) deporteRepositorio.findAll();
        return lista;
    }

    public Deporte getById(int id)
    {
        return deporteRepositorio.findById(id);
    }

    public void save(Deporte deporte)
    {
        deporteRepositorio.save(deporte);
    }

    public void delete(int id)
    {
        deporteRepositorio.deleteById(id);
    }

}
