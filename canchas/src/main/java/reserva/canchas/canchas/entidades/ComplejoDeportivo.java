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
  private List<Campo> campos;
  

  //TODO: pasar getter de campo a servicios
  public Campo getCampo(String deporte) {
    for (Campo campo : campos) {
      if (campo.getDeporte().getNombre().equals(deporte)) {
        return campo;
      }
    }
    return null;
  }
}