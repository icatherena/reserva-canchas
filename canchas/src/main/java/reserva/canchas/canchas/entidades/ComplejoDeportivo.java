package reserva.canchas.canchas.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ComplejoDeportivo {
  @Id
  @GeneratedValue
  private int id;
  
  private String ubicación;
  private String nombre;
  
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