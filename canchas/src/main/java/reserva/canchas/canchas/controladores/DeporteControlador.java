package reserva.canchas.canchas.controladores;

import reserva.canchas.canchas.entidades.*;
import reserva.canchas.canchas.servicios.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/deportes")
public class DeporteControlador implements WebMvcConfigurer {
    
    @Autowired
    DeporteServicio deporteServicio;

    @GetMapping
    public ModelAndView index() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Listado de Deportes");
        maw.addObject("vista", "deportes/index");
        maw.addObject("deportes", deporteServicio.getAll());
        return maw;
    }
    
    @GetMapping("/{id}")
    public ModelAndView one(@PathVariable("id") int id) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Detalle del Deporte #" + id);
        maw.addObject("vista", "deportes/ver");
        maw.addObject("deporte", deporteServicio.getById(id));
        return maw;
    }

    @GetMapping("/crear")
    public ModelAndView crear(Deporte deporte) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Crear Deporte");
        maw.addObject("vista", "deportes/crear");
        // Add any necessary model attributes here
        return maw;
    }

    @PostMapping("/crear")
    public ModelAndView guardar(@Valid Deporte deporte, BindingResult bindingResult) {
        ModelAndView maw = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            maw.setViewName("fragments/base");
            maw.addObject("titulo", "Crear Deporte");
            maw.addObject("vista", "deportes/crear");
            // Add any necessary model attributes here
            deporteServicio.save(deporte);
            return maw;
            
        }
        
        
        //TODO resolver hacia donde redireccionar
        //maw.setViewName("redirect:/api/deportes");
        return maw;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id, Deporte deporte) {
        return this.editar(id, deporte, true);
    }
    
    public ModelAndView editar(@PathVariable("id") int id, Deporte deporte, boolean estaPersistido) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Editar Deporte");
        maw.addObject("vista", "deportes/editar");
        //maw.addObject("deporte", deporte);

        if (estaPersistido) {
            maw.addObject("deporte", deporteServicio.getById(id));
        }
        return maw;
    }

    @PutMapping("/editar/{id}")
    public ModelAndView guardar(@PathVariable("id") int id, @Valid Deporte deporte, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return this.editar(id, deporte, false);
        }

        Deporte deporteAEditar = deporteServicio.getById(id);
        deporte.setId(deporteAEditar.getId());
        deporte.setNombre(deporteAEditar.getNombre());

        ModelAndView maw = this.index();
        
        deporteServicio.save(deporte);
        maw.addObject("exito", "Deporte modificado exitosamente");
        return maw;
    }

    @DeleteMapping("/{id}")
    private ModelAndView delete(@PathVariable("id") int id)
    {
        deporteServicio.delete(id);
        ModelAndView maw = this.index();
        maw.addObject("exito", "Deporte eliminado exitosamente");
        return maw;
    }
}
