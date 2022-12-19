package reserva.canchas.canchas.entidades;


import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Table(name="roles")
public class Rol {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @NotNull
  @NotBlank(message = "Campo obligatorio")
  @Size(max = 250, message= "Nombre demasiado largo")
  @Column(unique = true)
  private String nombre;
  
  @OneToMany
  private List<Permiso> permisos;
}