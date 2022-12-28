package reserva.canchas.canchas.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import reserva.canchas.canchas.entidades.Cancha;
import reserva.canchas.canchas.servicios.CanchaServicio;
import reserva.canchas.canchas.servicios.DeporteServicio;

@RestController
@RequestMapping("/canchas")
public class CanchaControlador implements WebMvcConfigurer {
    
    @Autowired
    CanchaServicio canchaServicio;

    @Autowired
    DeporteServicio deporteServicio;

    @GetMapping
    public ModelAndView index() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Listado de Canchas");
        maw.addObject("vista", "canchas/index");
        maw.addObject("canchas", canchaServicio.getAll());
        return maw;
    }

    @GetMapping("/{id}")
    public ModelAndView one(@PathVariable("id") int id) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Detalle de la Cancha #" + id);
        maw.addObject("vista", "canchas/ver");
        maw.addObject("cancha", canchaServicio.getById(id));
        return maw;
    }

    @GetMapping("/crear")
    public ModelAndView crear(Cancha cancha) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Crear Cancha");
        maw.addObject("vista", "canchas/crear");
        maw.addObject("deportes", deporteServicio.getAll());
        // Add any necessary model attributes here
        return maw;
    }
    
    @PostMapping("/crear")
    public ModelAndView guardar(@Valid Cancha cancha, BindingResult bindingResult) {
        ModelAndView maw = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            maw.setViewName("fragments/base");
            maw.addObject("titulo", "Crear Cancha");
            maw.addObject("vista", "canchas/crear");
            // Add any necessary model attributes here
            canchaServicio.save(cancha);
            return maw;
        }
        
        
        //TODO resolver hacia donde redireccionar
        //maw.setViewName("redirect:/api/canchas");
        return maw;
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id, Cancha cancha) {
        return this.editar(id, cancha, true);
    }

    public ModelAndView editar(@PathVariable("id") int id, Cancha cancha, boolean estaPersistido) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Editar Cancha");
        maw.addObject("vista", "canchas/editar");
    //    maw.addObject("cancha", cancha);
        maw.addObject("deportes", deporteServicio.getAll());
        
        if (estaPersistido) {
            maw.addObject("cancha", canchaServicio.getById(id));
        }
        return maw;
    }
    
    @PutMapping("/editar/{id}")
    public ModelAndView guardar(@PathVariable("id") int id, @Valid Cancha cancha, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return this.editar(id, cancha, false);
        }
        
        Cancha canchaAEditar = canchaServicio.getById(id);
        cancha.setId(canchaAEditar.getId());
        cancha.setUbicacionCancha(canchaAEditar.getUbicacionCancha());
        cancha.setDeporte(canchaAEditar.getDeporte());
        cancha.setMaterialCancha(canchaAEditar.getMaterialCancha());
    //    cancha.setTamaño(canchaAEditar.getTamaño());
        
        ModelAndView maw = this.index();
        
        canchaServicio.save(cancha);
        
        maw.addObject("exito", "Cancha modificada exitosamente");
        return maw;
    }

    @DeleteMapping("/{id}")
    private ModelAndView delete(@PathVariable("id") int id)
    {
        canchaServicio.delete(id);
        ModelAndView maw = this.index();
        maw.addObject("exito", "Cancha eliminada exitosamente");
        return maw;
    }
}

