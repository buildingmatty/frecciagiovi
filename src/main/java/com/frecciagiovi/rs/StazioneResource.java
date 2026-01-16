package com.frecciagiovi.rs;

import com.frecciagiovi.business.StazioneManager;
import com.frecciagiovi.dto.StazioneDTO;
import com.frecciagiovi.dto.StazioneMapper;
import com.frecciagiovi.model.Stazione;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/stazioni")
public class StazioneResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<StazioneDTO> getStazioni(){
        List<Stazione> stazioni = StazioneManager.getStazioni();
        return stazioni.stream().map(StazioneMapper::toDTO).toList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public StazioneDTO getStazione(@PathParam("id") Long id){
        StazioneDTO s = StazioneManager.getStazioneById(id);
        if(s == null){
            throw new NotFoundException("Stazione dato ID: "+ id +", non trovata");
        }
        return s;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response addStazione(Stazione s){
        Stazione add = StazioneManager.addStazione(s);
        return ResponseUtil.buildOkResponse(add);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateStazione(@PathParam("id") Long id, Stazione s){
        Stazione updated = StazioneManager.updateStazione(id, s);
        return ResponseUtil.buildOkResponse(updated);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteStazione(@PathParam("id") Long id){
        StazioneManager.deleteStazioneById(id);
        return Response.noContent().build();
    }
}
