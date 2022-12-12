package reserva.cancha;

// import java.util.regex.Pattern;

public class Usuario {
    
    // private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    
    private int idUsuario;
    private String nombreUsuario;
    private String email;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String email, String contraseña) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public void iniciarSesion () {
        
    }
    
    public void cerrarSesion () {
        
    }
    
    /* private void isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    } */
    
}