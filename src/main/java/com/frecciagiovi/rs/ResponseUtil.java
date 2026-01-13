package com.frecciagiovi.rs;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

//Classe dedicata alle response, questo ci permette di scrivere di meno...
public class ResponseUtil {
    public static Response buildOkResponse(Object entity){ //Object accetta qualsiasi tipo di variabile
        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    public static Response buildNotFoundResponse(String message){
        return Response
                .status(Response.Status.NOT_FOUND)
                .type(MediaType.TEXT_PLAIN)
                .entity(message)
                .build();
    }
    public static Response buildNoContentResponse(Object entity){
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    public static Response buildBadRequestResponse(String message){
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.TEXT_PLAIN)
                .entity(message)
                .build();
    }
}