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
public class Rol {
  @Id
  @GeneratedValue
  private int id;
  
  private String nombre;
  
  @OneToMany
  private List<Permiso> permisos;
}