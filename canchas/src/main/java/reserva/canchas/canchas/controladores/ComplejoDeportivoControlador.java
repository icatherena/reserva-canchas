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
@RequestMapping("/complejosdeportivos")
public class ComplejoDeportivoControlador implements WebMvcConfigurer {
    
    @Autowired
    ComplejoDeportivoServicio complejoDeportivoServicio;

    @Autowired
    DeporteServicio deporteServicio;

    @Autowired
    CanchaServicio canchaServicio;

    @GetMapping
    public ModelAndView index() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Listado de Complejos Deportivos");
        maw.addObject("vista", "complejosdeportivos/index");
        maw.addObject("complejosdeportivos", complejoDeportivoServicio.getAll());
        return maw;
    }

    @GetMapping("/{id}")
    public ModelAndView one(@PathVariable("id") int id) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Detalle del Complejo #" + id);
        maw.addObject("vista", "complejosdeportivos/ver");
        maw.addObject("complejodeportivo", complejoDeportivoServicio.getById(id));
        return maw;
    }

    @GetMapping("/crear")
    public ModelAndView crear(ComplejoDeportivo complejoDeportivo) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Crear Complejo");
        maw.addObject("vista", "complejosdeportivos/crear");
        maw.addObject("deportes", deporteServicio.getAll());
        maw.addObject("canchas", canchaServicio.getAll());
        // Add any necessary model attributes here
        return maw;
    }

    @PostMapping("/crear")
    public ModelAndView guardar(@Valid ComplejoDeportivo complejoDeportivo, BindingResult bindingResult) {
        ModelAndView maw = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            maw.setViewName("fragments/base");
            maw.addObject("titulo", "Crear Complejo");
            maw.addObject("vista", "complejosdeportivos/crear");
            // Add any necessary model attributes here
            complejoDeportivoServicio.save(complejoDeportivo);
            return maw;
        }
        
        
        //TODO resolver hacia donde redireccionar
        //maw.setViewName("redirect:/api/complejosdeportivos");
        return maw;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id, ComplejoDeportivo complejoDeportivo) {
        return this.editar(id, complejoDeportivo, true); 
    }
    
    public ModelAndView editar(@PathVariable("id") int id, ComplejoDeportivo complejoDeportivo, boolean estaPersistido) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Editar Complejo");
        maw.addObject("vista", "complejosdeportivos/editar");
    //    maw.addObject("complejodeportivo", complejoDeportivo);
        maw.addObject("deportes", deporteServicio.getAll());
        maw.addObject("canchas", canchaServicio.getAll()); //Idem
        
        if (estaPersistido) {
            maw.addObject("complejoDeportivo", complejoDeportivoServicio.getById(id));
        }
        return maw;
    }

    @PutMapping("/editar/{id}")
    public ModelAndView guardar(@PathVariable("id") int id, @Valid ComplejoDeportivo complejoDeportivo, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return this.editar(id, complejoDeportivo, false);
        }

        ComplejoDeportivo complejoDeportivoAEditar = complejoDeportivoServicio.getById(id);
        complejoDeportivo.setId(complejoDeportivoAEditar.getId());
        complejoDeportivo.setNombreComplejo(complejoDeportivoAEditar.getNombreComplejo());
        complejoDeportivo.setUbicacionComplejo(complejoDeportivoAEditar.getUbicacionComplejo());
        complejoDeportivo.setDeporte(complejoDeportivoAEditar.getDeporte());
        complejoDeportivo.setCancha(complejoDeportivoAEditar.getCancha());
       
        ModelAndView maw = this.index();
        
        complejoDeportivoServicio.save(complejoDeportivo);
        maw.addObject("exito", "Complejo modificado exitosamente");
        return maw;
    }

    @DeleteMapping("/{id}")
    private ModelAndView delete(@PathVariable("id") int id)
    {
        complejoDeportivoServicio.delete(id);
        ModelAndView maw = this.index();
        maw.addObject("exito", "Complejo eliminado exitosamente");
        return maw;
    }
}
