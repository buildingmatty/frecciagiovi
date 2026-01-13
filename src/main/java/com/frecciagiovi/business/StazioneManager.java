package com.frecciagiovi.business;

import com.frecciagiovi.dao.StazioneDao;
import com.frecciagiovi.model.Stazione;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StazioneManager {

    private static final Logger logger = LogManager.getLogger(StazioneManager.class.getName());

    public static Stazione getStazioneById(Long id){
        StazioneDao dao = new StazioneDao();

        try{
            return dao.getStazioneById(id);
        } finally {
            dao.close();
        }

    }

    public static List<Stazione> getStazioni(){
        StazioneDao dao = new StazioneDao();

        try{
            return dao.getAllStazioni();
        } finally {
            dao.close();
        }
    }
}
