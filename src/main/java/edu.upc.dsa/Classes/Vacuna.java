package edu.upc.dsa.Classes;

public class Vacuna {
    public Vacuna(){

    }
    private String vacunaId;
    private int cantidad = 0;

    public Vacuna(String vacunaId){
        this.vacunaId = vacunaId;
    }

    public void addVacuna() {

        this.cantidad++;
    }

    public String getVacunaId() {
        return vacunaId;
    }

    public void setVacunaId(String vacunaId) {
        this.vacunaId = vacunaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
