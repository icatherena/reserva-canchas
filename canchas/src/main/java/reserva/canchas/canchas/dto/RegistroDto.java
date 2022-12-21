package reserva.canchas.canchas.dto;

import reserva.canchas.canchas.validaciones.*;
import javax.validation.constraints.*;
import lombok.*;

@Data
@Confirmar
@EmailUnico
public class RegistroDto {

    @NotNull
    @NotEmpty(message = "Ingrese un nombre de usuario")
    private String usuario;

    @NotNull
    @NotEmpty(message = "Ingrese una dirección de correo electrónico")
    @Email(message = "Ingrese una dirección de correo electrónico válida")
    private String email;

    @NotNull
    @NotEmpty(message = "Ingrese una contraseña")
    private String contraseña;

    private String confirmar;

}
