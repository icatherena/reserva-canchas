package reserva.canchas.canchas.entidades;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Table(name="deportes")
public class Deporte {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  private String nombre;
}
