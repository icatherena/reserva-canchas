package reserva.canchas.canchas.entidades;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
  @Id
  @GeneratedValue
  private int id;
  
  private String nombreUsuario;
  private String contraseña;
  private String correo;
  
  @OneToMany
  private List<Rol> roles;
  
  @OneToMany
  private List<Alquiler> alquileres;

  @OneToOne
  private ComplejoDeportivo complejoDeportivo;
  
  public void iniciarSesión() {
    try {
      Authentication authenticate = Authentication.authenticate(
        new UsernamePasswordAuthenticationToken(nombreUsuario, contraseña)
      );
    } catch (AuthenticationException e) {
      // TODO: ver de que otra forma manejar la excepción.
      System.out.println("Login failed: " + e.getMessage());
    }
  }
  
  public void cerrarSesión() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      logoutHandler.logout(request, response, authentication);
    }
  }

  public void seleccionarDeporte(String deporte) {
    if (complejoDeportivo == null) {
      throw new InvalidOperationException("El usuario debe estar asociado a un complejo deportivo antes de seleccionar un deporte.");
    }
    if (!complejoDeportivo.getDeportes().contains(deporte)) {
      throw new InvalidOperationException("El deporte seleccionado no está disponible en el complejo deportivo del usuario");
    }
    this.deporte = deporte;
  }
}
