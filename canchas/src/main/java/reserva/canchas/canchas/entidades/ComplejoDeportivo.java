package reserva.canchas.canchas.entidades;

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
  
  @OneToMany
  private List<Deporte> deportes;
  
  @OneToMany
  private List<Cancha> Canchas;
  

  //TODO: pasar getter de Cancha a servicios
  public Cancha getCancha(String deporte) {
    for (Cancha Cancha : Canchas) {
      if (Cancha.getDeporte().getNombre().equals(deporte)) {
        return Cancha;
      }
    }
    return null;
  }
}