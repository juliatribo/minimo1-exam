package edu.upc.dsa.Classes;

import java.util.Date;

public class Vacunacion {
    public Vacunacion()
    {

    }
    private String vacunaId;
    private String userId;
    private String fecha;

    public Vacunacion(String vacunaId, String userId, String fecha)
    {
        this.vacunaId =vacunaId;
        this.userId=userId;
        this.fecha = fecha;
    }

    public String getVacunaId() {
        return vacunaId;
    }

    public void setVacunaId(String vacunaId) {
        this.vacunaId = vacunaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        fecha = fecha;
    }
}
