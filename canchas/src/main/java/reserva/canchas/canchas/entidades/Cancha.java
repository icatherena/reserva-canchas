package reserva.canchas.canchas.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="canchas")
public class Cancha {

  @Id
  @GeneratedValue
  private int id;
  
  private String ubicacion;
  private String tamaño;
  private String material;
  
  @OneToOne
  private Deporte deporte;
  
  @OneToMany
  private List<Alquiler> alquileres;

  //TODO: pasar metodos de alquilar, calcular y desponibilidad a servicios
  
  public Alquiler alquilar(Date fechaInicio, Date fechaFin, Usuario usuario) throws Exception {
    if (disponibilidad(fechaInicio, fechaFin)) {
      Alquiler alquiler = new Alquiler();
      alquiler.setFechaInicio(fechaInicio);
      alquiler.setFechaFin(fechaFin);
      alquiler.setUsuario(usuario);
      alquiler.setCancha(this);
      
      // Calcula el monto del alquiler según las fechas de alquiler y el tamaño y tipo del Cancha
      alquiler.setCantidad(calcularCantidad(fechaInicio, fechaFin, tamaño, material));
      
      alquileres.add(alquiler);
      return alquiler;
    } else {
      throw new Exception("The field is not available for the given dates.");
    }
  }

  private int calcularCantidad(Date fechaInicio, Date fechaFin, String tamaño, String material) {
    long diff = fechaFin.getTime() - fechaInicio.getTime();
    int numMinutes = (int) (diff / (60 * 1000));  // Calcula el numero de minutos
  
    if (tamaño.equals("pequeño")) {
      if (material.equals("grama")) {
        return (numMinutes * 10) / 60;  // Divide el monto del alquiler existente por 60 para obtener la tarifa por minuto
      } else if (material.equals("cemento")) {
        return (numMinutes * 15) / 60;
      } else {
        return (numMinutes * 20) / 60;
      }
    } else if (tamaño.equals("mediano")) {
      if (material.equals("grama")) {
        return (numMinutes * 15) / 60;
      } else if (material.equals("cemento")) {
        return (numMinutes * 20) / 60;
      } else {
        return (numMinutes * 25) / 60;
      }
    } else {
      if (material.equals("grama")) {
        return (numMinutes * 20) / 60;
      } else if (material.equals("cemento")) {
        return (numMinutes * 25) / 60;
      } else {
        return (numMinutes * 30) / 60;
      }
    }
  }
  
  
  public boolean disponibilidad(Date fechaInicio, Date fechaFin) {
    for (Alquiler alquiler : alquileres) {
      if (alquiler.getFechaInicio().before(fechaFin) && alquiler.getFechaFin().after(fechaInicio)) {
        return false;
      }
    }
    return true;
  }
}