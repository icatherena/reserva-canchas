package reserva.canchas.canchas.entidades;


import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Table(name="alquileres")
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