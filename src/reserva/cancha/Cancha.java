package reserva.cancha;

import java.util.Date;

public class Cancha {
    
    private int idCancha;
    private String ubicacionCancha; //tipo ComplejoDeportivo?
    private String tamaño;
    private String tipo;
    private Deporte deporte;

    public Cancha() {
    }

    public Cancha(int idCancha, String ubicacionCancha, String tamaño, String tipo, Deporte deporte) {
        this.idCancha = idCancha;
        this.ubicacionCancha = ubicacionCancha;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.deporte = deporte;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public String getUbicacionCancha() {
        return ubicacionCancha;
    }

    public void setUbicacionCancha(String ubicacionCancha) {
        this.ubicacionCancha = ubicacionCancha;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
   
    
    public void ReservarCancha(Usuario idUsuario, Date fechaInicio, Date fechaFin, Cancha cancha) {
        
    }
    
    public boolean CanchaLibre(Date fechaInicio, Date fechaFin, Cancha cancha) {       
       // return false;        
    }

}
