package reserva.cancha;

public class ComplejoDeportivo {
    
    private int idComplejo;
    private String nombreComplejo;
    private String ubicacionComplejo;
    private Deporte deporte;

    public ComplejoDeportivo() {
    }

    public ComplejoDeportivo(int idComplejo, String nombreComplejo, String ubicacionComplejo, Deporte deporte) {
        this.idComplejo = idComplejo;
        this.nombreComplejo = nombreComplejo;
        this.ubicacionComplejo = ubicacionComplejo;
        this.deporte = deporte;
    }

    public int getIdComplejo() {
        return idComplejo;
    }

    public void setIdComplejo(int idComplejo) {
        this.idComplejo = idComplejo;
    }

    public String getNombreComplejo() {
        return nombreComplejo;
    }

    public void setNombreComplejo(String nombreComplejo) {
        this.nombreComplejo = nombreComplejo;
    }

    public String getUbicacionComplejo() {
        return ubicacionComplejo;
    }

    public void setUbicacionComplejo(String ubicacionComplejo) {
        this.ubicacionComplejo = ubicacionComplejo;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
    
    
    
}
