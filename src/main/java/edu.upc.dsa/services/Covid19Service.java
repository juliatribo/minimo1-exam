package edu.upc.dsa.services;
import edu.upc.dsa.Classes.Usuario;
import edu.upc.dsa.Classes.Seguimiento;
import edu.upc.dsa.Classes.Vacunacion;
import edu.upc.dsa.Classes.Vacuna;
import edu.upc.dsa.Covid19Managerimpl;
import edu.upc.dsa.Covid19Manager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Api(value = "/covid", description = "Endpoint to Covid Service")
@Path("/covid")

public class Covid19Service {
    private Covid19Manager cm;

    public Covid19Service(){
        this.cm = Covid19Managerimpl.getInstance();
        if (!this.cm.isDirty() ) {

            Usuario user1 = new Usuario("Julia");
            Usuario user2 = new Usuario("Maria");

            cm.addUsuario(user1);
            cm.addUsuario(user2);

            Vacuna moderna = new Vacuna("Moderna");
            Vacuna pfizer = new Vacuna("Pfizer");

            cm.addVacuna(moderna);
            cm.addVacuna(pfizer);


            cm.dirty();
        }
    }
    @GET
    @ApiOperation(value = "get vacunas by cantidad", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacuna.class, responseContainer="List"),
    })
    @Path("/cantidad")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacunaByCantidad() {

        List<Vacuna> listaVacuna = this.cm.ordenDescendenteCantidad();

        GenericEntity<List<Vacuna>> entity = new GenericEntity<List<Vacuna>>(listaVacuna) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get vacunacion by orden vacunas y fecha vacunacion", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacunacion.class, responseContainer="List"),
    })
    @Path("/ordenVacunacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacunacionOrden() {

        List<Vacunacion> listaVacunacion = this.cm.ordenVacunasYVacunacion();

        GenericEntity<List<Vacunacion>> entity = new GenericEntity<List<Vacunacion>>(listaVacunacion) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get vacunacion by orden vacunas y fecha vacunacion", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Seguimiento.class, responseContainer="List"),
    })
    @Path("/seguimientosByUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getseguimientosByUser(@PathParam("userId") String userId) {

        List<Seguimiento> listaSeg = this.cm.seguimientosByUser(userId);

        GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(listaSeg) {};
        return Response.status(201).entity(entity).build();

    }


    @POST
    @ApiOperation(value = "vacunar", notes = "a la fecha separar con -")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/vacunar/{userId}/{vacunaId}/{fecha}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newVacuna(@PathParam("userId") String userId,@PathParam("vacunaId") String vacunaId,@PathParam("fecha") String fecha) {
        if (userId.isEmpty()||vacunaId.isEmpty()||fecha.isEmpty())  return Response.status(500).build();
        this.cm.vacunar(userId,vacunaId,fecha);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "seguimiento", notes = "a la fecha separar con -")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/seguimiento/{userId}/{fecha}/{description}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newSeguimiento(@PathParam("userId") String userId,@PathParam("fecha") String fecha,@PathParam("description") String desc) {
        //if (userId.isEmpty()||desc.isEmpty()||fecha.isEmpty())  return Response.status(500).build();
        this.cm.addSeguimiento(userId,fecha,desc);
        return Response.status(201).build();
    }







}
