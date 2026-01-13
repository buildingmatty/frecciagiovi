package com.frecciagiovi.rs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

//La serializzazione o deserializzazione è una conversione
@Provider
public class JacksonConfiguration implements ContextResolver<ObjectMapper> {
    private ObjectMapper objectMapper;

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        objectMapper = new ObjectMapper();

        /*
        SimpleModule module = new SimpleModule(); //Questo SimpleModule mi permette
        // di settare la data e/o l'ora nel formato e ordine che preferiamo, esempio:
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        objectMapper.registerModule(module); //Questo è fondamentale, perchè registra il "modulo"
         */

        //ObjectMapper utilizza il modulo JavaTimeModule per Serializzare/Deserializzare
        objectMapper.registerModule(new JavaTimeModule()); //JavaTimeModule ha al suo interno i Serializzatori e Deseriallizatori per LocalTime, LocalDate e LocalDateTime
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true); //Gli dice di stampare gli Enum come stringhe
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper;
    }
}
