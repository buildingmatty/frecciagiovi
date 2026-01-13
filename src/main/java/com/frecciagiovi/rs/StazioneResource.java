package com.frecciagiovi.rs;

import com.frecciagiovi.business.StazioneManager;
import com.frecciagiovi.dao.StazioneDao;
import com.frecciagiovi.model.Stazione;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/stazioni")
public class StazioneResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Stazione> getStazioni(){
        return StazioneManager.getStazioni();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Stazione getStazione(@PathParam("id") Long id){
        StazioneDao dao = new StazioneDao();

        try {
            Stazione s = dao.getStazioneById(id);
            if(s==null){
                throw new NotFoundException("Stazione non trovata" + id);
            }
            return s;
        } finally {
            dao.close();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Stazione createStazione(Stazione s){
        if (s == null || s.getNomeStazione() == null) {

        }
    }
}
