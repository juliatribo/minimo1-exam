package edu.upc.dsa.Classes;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    public Usuario(){

    }

    private String userId;
    private List <Seguimiento> seguimientos= new ArrayList<Seguimiento>();

    public Usuario(String userId){
        this.userId = userId;
    }
    public void nuevoSeguimiento(Seguimiento seguimiento){
        seguimientos.add(seguimiento);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }
}
