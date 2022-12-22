package reserva.canchas.canchas.controladores;

//import reserva.canchas.canchas.entidades.*;
//import reserva.canchas.canchas.servicios.*;

//import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeControlador {
    
    //¿@Autowired?

    @RequestMapping("/")
    public ModelAndView home()
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Inicio");
        maw.addObject("vista", "inicio/home");
        return maw;  
    }

}
