package reserva.canchas.canchas.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Table(name="complejosdeportivos")
public class ComplejoDeportivo {
  @Id
  @GeneratedValue
  private int id;
  
  private String ubicacionComplejo;
  private String nombreComplejo;
  
  @OneToOne
  private Deporte deporte;
  //private List<Deporte> deportes;
  
  @OneToOne
  private Cancha cancha;
  //private List<Cancha> Canchas;
  
  public void getDeportes(Deporte deporte) {
    this.deporte = deporte;
  }

  public void getCanchas(Cancha cancha) {
    this.cancha = cancha;
  } 

  /* public Cancha getCancha(String deporte) {
    for (Cancha Cancha : canchas) {
      if (Cancha.getDeporte().getNombre().equals(deporte)) {
        return Cancha;
      }
    }
    return null;
  } */

}