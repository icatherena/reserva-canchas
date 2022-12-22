package reserva.canchas.canchas.entidades;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="usuarios")
public class Usuario {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @NotNull
  @Column(unique = true)
  private String nombreUsuario;
  
  @NotNull
  private String contraseña;
  
  @NotNull
  @Column(unique = true)
  private String email;
  
  @ManyToOne
  @JsonBackReference
  @NotNull
  private Rol rol; //No puede usarse lista para el servicio
  
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
