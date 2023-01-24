/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.controller;

import com.ifpe.dsc.indebate.model.Debate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilh
 */
public class TesteJpa {
    
      private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("indebate2");
    private static final Logger logger = Logger.getGlobal();
    
    static {
        logger.setLevel(Level.INFO);
    }
    
    public static void main(String[] args) {
        try {
            int id = inserirUsuario();
            //consultarUsuario(id);
        } finally {
            emf.close();
        }
    }
    
    
    public static int inserirUsuario() {
        Debate debate = new Debate();
        debate.setData(new Date());
        
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(debate);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                logger.log(Level.SEVERE,
                        "Cancelando transação com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                logger.info("Transação cancelada.");
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return debate.getId();
    }
 

}
