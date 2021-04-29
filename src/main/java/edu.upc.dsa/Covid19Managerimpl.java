package edu.upc.dsa;

import edu.upc.dsa.Classes.*;
import org.apache.log4j.Logger;

import java.security.Permission;
import java.util.*;

public class Covid19Managerimpl implements Covid19Manager {


    private ArrayList<Vacuna> arrayVacunas;
    private List<Vacunacion> listVacunaciones;
    private List<Seguimiento> listSeguimiento;
    private HashMap<String, Usuario > hmUsers;


    final static Logger logger = Logger.getLogger(Covid19Managerimpl.class);
    private boolean dirty=false;

    private static Covid19Manager instance;

   private Covid19Managerimpl() {
       arrayVacunas = new ArrayList<>();
       listVacunaciones = new ArrayList<>();
       listSeguimiento = new ArrayList<>();
       hmUsers = new HashMap<String,Usuario>();
   }

   public void addVacuna(Vacuna vacuna){
       logger.info("new vacuna " + vacuna.getVacunaId());
       arrayVacunas.add(vacuna);
       logger.info("new vacuna added");
   }

   public void addSeg(Seguimiento seguimiento){
       logger.info("new seguimiento " + seguimiento.getFecha());
       listSeguimiento.add(seguimiento);
       logger.info("new seguimiento added");
   }

    public void addUsuario(Usuario usuario){
        logger.info("new user " + usuario.getUserId());
        hmUsers.put(usuario.getUserId(),usuario);
        logger.info("new user added");
    }

    public void addVacunaciones(Vacunacion vacunacion){
        logger.info("new vacunacion " + vacunacion.getVacunaId());
        listVacunaciones.add(vacunacion);
        logger.info("new vacunacion added");
    }

    @Override
    public void dirty() {
        this.dirty = true;
    }

    public boolean isDirty(){
       return dirty;
    }

    public void clear(){

        arrayVacunas.clear();
        listVacunaciones.clear();
        listSeguimiento.clear();
        hmUsers.clear();

   }

    public static Covid19Manager getInstance() {
        if (instance==null)
        {
            instance = new Covid19Managerimpl();
        }

        return instance;
    }

    public void vacunar(String usuarioId, String vacunaId, String fecha){
        if (hmUsers.get(usuarioId)==null)
        {
            logger.warn("user"+ usuarioId + "not found ");
        }
        logger.info("vacunar a "+usuarioId);
        Vacunacion vacunacion= new Vacunacion(vacunaId,usuarioId,fecha);
        listVacunaciones.add(vacunacion);
        for (int i = 0; i<arrayVacunas.size();i++)
        {
            if (vacunaId.equals(arrayVacunas.get(i).getVacunaId())){
                arrayVacunas.get(i).addVacuna();
            }
        }
        logger.info("vacuna "+vacunaId+" added to "+usuarioId+ " en la fecha "+fecha);

    }
    public List<Vacunacion> ordenVacunasYVacunacion(){
        List<Vacunacion> p = listVacunaciones;

        logger.info("getVacunaciones por orden");
        Collections.sort(p, new Comparator<Vacunacion>() {
            @Override
            public int compare(Vacunacion v1, Vacunacion v2) {
                return v1.getFecha().compareTo(v2.getFecha());
            }
        });

        logger.info("getVacunaciones por Marca");
        Collections.sort(p, new Comparator<Vacunacion>() {
            @Override
            public int compare(Vacunacion v1, Vacunacion v2) {
                return v1.getVacunaId().compareTo(v2.getVacunaId());
            }
        });
        logger.info("the first Vacunacion is: " + p.get(0).getVacunaId());

        return p;
    }

    public List<Vacuna> ordenDescendenteCantidad(){

        List<Vacuna> p = arrayVacunas;

        logger.info("getMarcasByCantidad");
        Collections.sort(p, new Comparator<Vacuna>() {
            @Override
            public int compare(Vacuna v1, Vacuna v2) {
                return v2.getCantidad()-v1.getCantidad();
            }
        });
        logger.info("the first Vacuna is: " + p.get(0).getVacunaId());

        return p;

    }

    public void addSeguimiento(String userId,String fecha, String descripcion){
        if (hmUsers.get(userId)==null)
        {
            logger.warn("user"+ userId + "not found ");
        }
        logger.info("add seguimiento");
        Seguimiento seguimiento = new Seguimiento(fecha, descripcion);
        Usuario user = hmUsers.get(userId);
        user.nuevoSeguimiento(seguimiento);
        listSeguimiento.add(seguimiento);
        logger.info("seguimiento added to" +userId);
    }

    public List<Seguimiento> seguimientosByUser(String userId){
        logger.info("get Seguimientos By User");
        Usuario user = hmUsers.get(userId);
        return user.getSeguimientos();
    }


}
