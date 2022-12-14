package reserva.canchas.canchas.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Deporte {
  @Id
  @GeneratedValue
  private int id;
  
  private String nombre;
}