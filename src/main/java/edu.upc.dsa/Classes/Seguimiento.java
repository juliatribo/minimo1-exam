package edu.upc.dsa.Classes;

import java.util.Date;

public class Seguimiento {
    public Seguimiento(){

    }
    private String fecha;
    private String descripcion;
    public Seguimiento( String fecha, String descripcion){
        this.fecha = fecha;
        this.descripcion = descripcion;

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
