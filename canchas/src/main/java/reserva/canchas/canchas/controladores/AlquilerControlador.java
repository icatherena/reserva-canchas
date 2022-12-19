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

@RestController
@RequestMapping("/reservas")
public class AlquilerControlador implements WebMvcConfigurer {
  
  @Autowired
  private AlquilerServicio alquilerServicio;
  
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
    // Add any necessary model attributes here
    return maw;
  }
  
  @PostMapping("/crear")
  public ModelAndView guardar(Alquiler alquiler, BindingResult bindingResult) {
    ModelAndView maw = new ModelAndView();
    if (bindingResult.hasErrors()) {
      maw.setViewName("fragments/base");
      maw.addObject("titulo", "Crear alquiler");
      maw.addObject("vista", "alquileres/crear");
      // Add any necessary model attributes here
      return maw;
    }
    
    alquilerServicio.save(alquiler);
    //TODO resolver hacia donde redireccionar
    maw.setViewName("redirect:/api/alquileres");
    return maw;
  }
  
  /* Atributos de editar: 
  * Una lista de objetos Cancha para completar un menú desplegable en el formulario
  * Una lista de objetos Usuario para llenar un menú desplegable en el formulario
  * Una lista de objetos ComplejoDeportivo para llenar un menú desplegable en el formulario
  * El objeto Alquiler que se está editando, con sus propiedades rellenadas previamente en los campos del formulario */
  @GetMapping("/editar/{id}")
  public ModelAndView editar(@PathVariable("id") int id, Alquiler alquiler) {
    Alquiler alquilerAEditar = alquilerServicio.getById(id);
    alquiler.setId(alquilerAEditar.getId());
    alquiler.setFechaInicio(alquilerAEditar.getFechaInicio());
    alquiler.setFechaFin(alquilerAEditar.getFechaFin());
    alquiler.setCantidad(alquilerAEditar.getCantidad());
    alquiler.setCancha(alquilerAEditar.getCancha());
    alquiler.setUsuario(alquilerAEditar.getUsuario());
    alquiler.setComplejo(alquilerAEditar.getComplejo());
    return this.editar(id, alquiler);
  }
  
  public ModelAndView editar(@PathVariable("id") int id, Alquiler alquiler, boolean estaPersistido) {
    ModelAndView maw = new ModelAndView();
    maw.setViewName("fragments/base");
    maw.addObject("titulo", "Editar alquiler");
    maw.addObject("vista", "alquileres/editar");
    maw.addObject("alquiler", alquiler);
    maw.addObject("canchas", canchaServicio.getAll());//TODO crear el crud de canchaServicio
    maw.addObject("usuarios", UsuarioServicio.getAll());
    maw.addObject("complejos", complejoServicio.getAll());//TODO crear el crud de complejoServicio
    return maw;
  }
  
  @PutMapping("/editar/{id}")
  public ModelAndView guardar(@Valid Alquiler alquiler, BindingResult br, RedirectAttributes ra) {
    if (br.hasErrors()) {
      return this.editar(alquiler.getId(), alquiler, false);
    }
    alquilerServicio.save(alquiler);
    ModelAndView maw = this.index();
    maw.addObject("exito", "Alquiler modificado exitosamente");
    return maw;
  }
  
  @DeleteMapping("eliminar/{id}")
  private ModelAndView eliminar(@PathVariable("id") int id)
  {
    alquilerServicio.delete(id);
    ModelAndView maw = this.index();
    maw.addObject("exito", "Alquiler borrado exitosamente");
    return maw;
  }
  
}
