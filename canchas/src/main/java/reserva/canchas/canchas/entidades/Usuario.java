package reserva.canchas.canchas.entidades;
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

  private String deporte;


  public boolean authenticate(String nombreUsuario, String contraseña) {
    return this.nombreUsuario.equals(nombreUsuario) && this.contraseña.equals(contraseña);
  }
  

  public void seleccionarDeporte(String deporte) throws Exception {
    if (complejoDeportivo == null) {
      throw new Exception("El usuario debe estar asociado a un complejo deportivo antes de seleccionar un deporte.");
    }
    if (!complejoDeportivo.getDeportes().contains(deporte)) {
      throw new Exception("El deporte seleccionado no está disponible en el complejo deportivo del usuario");
    }
    this.deporte = deporte;
  }
}
