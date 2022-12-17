package reserva.canchas.canchas.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;



@Entity
@Getter
public class Deporte {

  @Id
  @GeneratedValue
  private int id;
  
  private String nombre;
}
