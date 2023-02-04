/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Partido;
import com.ifpe.dsc.indebate.model.Politico;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Madson Rocha
 */
public class PartidoTeste extends Teste {
    
    @Test
    public void persistirPartido() {
        
        Partido partido = new Partido();
        
        partido.setNome("Partido persistido");
        
        em.persist(partido);
        em.flush();
        
        assertNotNull(partido.getId());

    }
    
    @Test
    public void atualizarPartido(){
        
        TypedQuery<Partido> query = em.createNamedQuery("Partido.PorNome", Partido.class);
        query.setParameter("nome", "Partido dos gamers");
        Partido partido = query.getSingleResult();
        assertNotNull(partido);
        partido.setNome("Partido dos jogadores");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Partido dos jogadores");
        partido = query.getSingleResult();
        assertNotNull(partido);
        
    }
    
    @Test
    public void atualizarPartidoMerge(){

        TypedQuery<Partido> query = em.createNamedQuery("Partido.PorNome", Partido.class);
        query.setParameter("nome", "Partido dos kids");
        Partido partido = query.getSingleResult();
        assertNotNull(partido);
        partido.setNome("Partido dos choradores");
        em.clear();       
        em.merge(partido);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
    
    @Test
    public void deletarPartido(){
        
        TypedQuery<Partido> query = em.createNamedQuery("Partido.PorNome", Partido.class);
        query.setParameter("nome", "Partido dos lacradores");
        Partido partido = query.getSingleResult();
        assertNotNull(partido);
        em.remove(partido);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
   
}
