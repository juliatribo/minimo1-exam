package edu.upc.dsa;
import edu.upc.dsa.Classes.Usuario;
import edu.upc.dsa.Classes.Seguimiento;
import edu.upc.dsa.Classes.Vacunacion;
import edu.upc.dsa.Classes.Vacuna;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class Covid19ManagerTest {

    Covid19Manager cm = Covid19Managerimpl.getInstance();

    private Usuario user1;
    private Usuario user2;
    private Vacuna moderna;
    private Vacuna pfizer;

    @Before
    public void init(){

        user1 = new Usuario("Julia");
        user2 = new Usuario("Maria");

        cm.addUsuario(user1);
        cm.addUsuario(user2);

        moderna = new Vacuna("Moderna");
        pfizer = new Vacuna("Pfizer");

        cm.addVacuna(moderna);
        cm.addVacuna(pfizer);
    }

    @After
    public void reset(){
        cm.clear();
    }


    @Test
    public void test1(){
        cm.vacunar(user1.getUserId(),moderna.getVacunaId(),"10/1/2021");
        Assert.assertEquals(1, cm.ordenVacunasYVacunacion().size());
    }

    @Test
    public void test2(){
        cm.addSeguimiento(user1.getUserId(),"11/1/2021","me duele la cabecita");
        Assert.assertEquals("me duele la cabecita",user1.getSeguimientos().get(0).getDescripcion());
    }





}
