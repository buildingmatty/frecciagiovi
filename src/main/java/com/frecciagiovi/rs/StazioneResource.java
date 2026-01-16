package com.frecciagiovi.rs;

import com.frecciagiovi.business.StazioneManager;
import com.frecciagiovi.model.Stazione;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/stazioni")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StazioneResource {

    @GET
    @Path("/")
    public List<Stazione> getStazioni(){
        return StazioneManager.getStazioni();
    }

    @GET
    @Path("/{id}")
    public Stazione getStazione(@PathParam("id") Long id){
        Stazione s = StazioneManager.getStazioneById(id);
        if(s == null){
            throw new NotFoundException("Stazione dato ID: "+ id +", non trovata");
        }
        return s;
    }

    @POST
    @Path("/")
    public Response addStazione(Stazione s){
        Stazione add = StazioneManager.addStazione(s);
        return ResponseUtil.buildOkResponse(add);
    }

    @PUT
    @Path("/{id}")
    public Response updateStazione(@PathParam("id") Long id, Stazione s){
        Stazione updated = StazioneManager.updateStazione(id, s);
        return ResponseUtil.buildOkResponse(updated);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStazione(@PathParam("id") Long id){
        StazioneManager.deleteStazioneById(id);
        return Response.noContent().build();
    }
}
