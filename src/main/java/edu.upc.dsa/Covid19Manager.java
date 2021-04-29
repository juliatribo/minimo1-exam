package edu.upc.dsa;

import edu.upc.dsa.Classes.Seguimiento;
import edu.upc.dsa.Classes.Usuario;
import edu.upc.dsa.Classes.Vacuna;
import edu.upc.dsa.Classes.Vacunacion;

import java.util.Date;
import java.util.List;


public interface Covid19Manager {

    public void clear();
    public void dirty();
    public boolean isDirty();
    public void vacunar(String usuarioId, String vacunaId,  String fecha);
    public List<Vacunacion> ordenVacunasYVacunacion();
    public List<Vacuna> ordenDescendenteCantidad();
    public void addSeguimiento(String userId,String fecha, String descripcion);
    public List<Seguimiento> seguimientosByUser(String userId);
    public void addVacuna(Vacuna vacuna);
    public void addSeg(Seguimiento seguimiento);
    public void addUsuario(Usuario usuario);
    public void addVacunaciones(Vacunacion vacunacion);


}
