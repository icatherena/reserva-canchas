package reserva.canchas.canchas.entidades;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Alquiler {
  @Id
  @GeneratedValue
  private int id;
  
  private Date fechaInicio;
  private Date fechaFin;
  private int cantidad;
  
  @OneToOne
  private Cancha Cancha;
  
  @OneToOne
  private Usuario usuario;

  @OneToOne
  private ComplejoDeportivo Complejo;
}