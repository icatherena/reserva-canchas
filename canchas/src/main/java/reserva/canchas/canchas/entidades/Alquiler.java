package reserva.canchas.canchas.entidades;

import java.io.Serializable;

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
public class Alquiler implements Serializable {
  @Id
  @GeneratedValue
  private int id;
  
  private Date fechaInicio;
  private Date fechaFin;
  private int cantidad;
  
  @OneToOne
  private Cancha cancha;
  
  @OneToOne
  private Usuario usuario;

  @OneToOne
  private Deporte deporte;

  @OneToOne
  private ComplejoDeportivo complejoDeportivo;

  public Alquiler(int id, ComplejoDeportivo complejoDeportivo, Deporte deporte, Cancha cancha, Date fechaInicio, Date fechaFin) {
    this.id = id;
    this.complejoDeportivo = complejoDeportivo;
    this.deporte = deporte;
    this.cancha = cancha;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
}

}