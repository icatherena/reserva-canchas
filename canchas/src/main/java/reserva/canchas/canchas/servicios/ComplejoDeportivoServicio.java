package reserva.canchas.canchas.servicios;

import reserva.canchas.canchas.entidades.*;
import reserva.canchas.canchas.repositorios.*;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ComplejoDeportivoServicio {
    
    
    @Autowired
    ComplejoDeportivoRepositorio complejoDeportivoRepositorio;

    public List<ComplejoDeportivo> getAll()
    {
        List<ComplejoDeportivo> lista = new ArrayList<ComplejoDeportivo>();
        lista = (ArrayList<ComplejoDeportivo>) complejoDeportivoRepositorio.findAll();
        return lista;
    }

    public ComplejoDeportivo getById(int id)
    {
        return complejoDeportivoRepositorio.findById(id);
    }

    public void save(ComplejoDeportivo complejoDeportivo)
    {
        complejoDeportivoRepositorio.save(complejoDeportivo);
    }

    public void delete(int id)
    {
        complejoDeportivoRepositorio.deleteById(id);
    }

}
