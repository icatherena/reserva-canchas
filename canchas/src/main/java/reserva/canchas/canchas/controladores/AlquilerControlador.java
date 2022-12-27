package reserva.canchas.canchas.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import reserva.canchas.canchas.entidades.Alquiler;
import reserva.canchas.canchas.servicios.AlquilerServicio;
import reserva.canchas.canchas.servicios.UsuarioServicio;
import reserva.canchas.canchas.servicios.CanchaServicio;
import reserva.canchas.canchas.servicios.DeporteServicio;
import reserva.canchas.canchas.servicios.ComplejoDeportivoServicio;

@RestController
@RequestMapping("/alquileres")
public class AlquilerControlador implements WebMvcConfigurer {
  
  @Autowired
  private AlquilerServicio alquilerServicio;

  @Autowired
  private ComplejoDeportivoServicio complejoDeportivoServicio;

  @Autowired
  private DeporteServicio deporteServicio;

  @Autowired
  private CanchaServicio canchaServicio;

  @Autowired
  private UsuarioServicio usuarioServicio;
  
  @GetMapping
  public ModelAndView index() {
    ModelAndView maw = new ModelAndView();
    maw.setViewName("fragments/base");
    maw.addObject("titulo", "Listado de alquileres");
    maw.addObject("vista", "alquileres/index");
    maw.addObject("alquileres", alquilerServicio.getAll());
    return maw; 
  }
  
  @GetMapping("/{id}")
  public ModelAndView one(@PathVariable("id") int id) {
    ModelAndView maw = new ModelAndView();
    maw.setViewName("fragments/base");
    maw.addObject("titulo", "Detalle del alquiler #" + id);
    maw.addObject("vista", "alquileres/ver");
    maw.addObject("alquiler", alquilerServicio.getById(id));
    return maw;
  }
  
  @GetMapping("/crear")
  public ModelAndView crear(Alquiler alquiler) {
    ModelAndView maw = new ModelAndView();
    maw.setViewName("fragments/base");
    maw.addObject("titulo", "Crear alquiler");
    maw.addObject("vista", "alquileres/crear");
    maw.addObject("complejosDeportivos", complejoDeportivoServicio.getAll());
    maw.addObject("deportes", deporteServicio.getAll());
    maw.addObject("canchas", canchaServicio.getAll());
    // Add any necessary model attributes here
    return maw;
  }
  
  @PostMapping("/crear")
  public ModelAndView guardar(@Valid Alquiler alquiler, BindingResult bindingResult) {
    ModelAndView maw = new ModelAndView();
    if (!bindingResult.hasErrors()) {
      maw.setViewName("fragments/base");
      maw.addObject("titulo", "Crear alquiler");
      maw.addObject("vista", "alquileres/crear");
      // Add any necessary model attributes here
      alquilerServicio.save(alquiler);
      return maw;
    }
    
    
    //TODO resolver hacia donde redireccionar
  //  maw.setViewName("redirect:/api/alquileres");
    return maw;
  }
  
  /* Atributos de editar: 
  * Una lista de objetos Cancha para completar un menú desplegable en el formulario
  * Una lista de objetos Usuario para llenar un menú desplegable en el formulario
  * Una lista de objetos ComplejoDeportivo para llenar un menú desplegable en el formulario
  * El objeto Alquiler que se está editando, con sus propiedades rellenadas previamente en los campos del formulario */
  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable("id") int id, Alquiler alquiler) {
    return this.editar(id, alquiler, true);
  }
  
  public ModelAndView editar(@PathVariable("id") int id, Alquiler alquiler, boolean estaPersistido) {
    ModelAndView maw = new ModelAndView();
    maw.setViewName("fragments/base");
    maw.addObject("titulo", "Editar alquiler");
    maw.addObject("vista", "alquileres/editar");
  //  maw.addObject("alquiler", alquiler);
    maw.addObject("canchas", canchaServicio.getAll()); 
    maw.addObject("usuarios", usuarioServicio.getAll());
    maw.addObject("complejos", complejoDeportivoServicio.getAll()); 
    maw.addObject("deportes", deporteServicio.getAll());
    
    if (estaPersistido) {
      maw.addObject("alquiler", alquilerServicio.getById(id));
    }
    return maw;
  }
  
  @PutMapping("/editar/{id}")
  public ModelAndView guardar(@PathVariable("id") int id, @Valid Alquiler alquiler, BindingResult br, RedirectAttributes ra) {
    if (br.hasErrors()) {
      return this.editar(id, alquiler, false);
    }
    
    Alquiler alquilerAEditar = alquilerServicio.getById(id);
    alquiler.setId(alquilerAEditar.getId());
    alquiler.setFechaInicio(alquilerAEditar.getFechaInicio());
    alquiler.setFechaFin(alquilerAEditar.getFechaFin());
    alquiler.setDeporte(alquilerAEditar.getDeporte());
    alquiler.setCancha(alquilerAEditar.getCancha());
    alquiler.setUsuario(alquilerAEditar.getUsuario());
    alquiler.setComplejoDeportivo(alquilerAEditar.getComplejoDeportivo());

    ModelAndView maw = this.index();
    
    alquilerServicio.save(alquiler);
    maw.addObject("exito", "Reserva modificada exitosamente");
    return maw;
  }

  @DeleteMapping("/{id}")
  private ModelAndView delete(@PathVariable("id") int id)
  {
    alquilerServicio.delete(id);
    ModelAndView maw = this.index();
    maw.addObject("exito", "Reserva eliminada exitosamente");
    return maw;
  }
  
}