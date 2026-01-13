package com.frecciagiovi.rs;

import com.frecciagiovi.dao.DaoUtil;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    private static final Logger logger = LogManager.getLogger(RestApplication.class);

    @Override
    public Set<Class<?>> getClasses() {

        logger.info("Avvio della REST application");

        DaoUtil.init();

        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(StazioneResource.class);
        classes.add(ValidationExceptionMapper.class);
        classes.add(JacksonConfiguration.class);

        return classes;
    }
}
