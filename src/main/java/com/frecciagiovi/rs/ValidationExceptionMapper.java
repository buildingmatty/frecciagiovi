package com.frecciagiovi.rs;

import com.frecciagiovi.business.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider //Serve per dire a rest easy che è un componente che deve utilizzare
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException exception) { //Ritorna una response
        return ResponseUtil.buildBadRequestResponse(exception.getMessage()); //Facciamo capire al client quale è stato l'errore nel dettaglio
    }
}
