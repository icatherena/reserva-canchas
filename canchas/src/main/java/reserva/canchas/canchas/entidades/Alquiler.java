package reserva.canchas.canchas.entidades;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="alquileres")
public class Alquiler implements Serializable {
  @Id
  @GeneratedValue
  private int id;
  
  @Basic
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date fechaInicio;

  @Basic
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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