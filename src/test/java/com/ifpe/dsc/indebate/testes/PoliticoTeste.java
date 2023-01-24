package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Politico;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vagner França
 */
public class PoliticoTeste extends Teste{
    
    @Test
    public void atualizarPolitico(){
        
        TypedQuery<Politico> query = em.createNamedQuery("Politico.PorNome", Politico.class);
        query.setParameter("nome", "Politico press F");
        Politico politico = query.getSingleResult();
        assertNotNull(politico);
        politico.setNome("Politico GG");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Politico GG");
        politico = query.getSingleResult();
        assertNotNull(politico);
        
    }
    
    @Test
    public void atualizarPoliticoMerge(){

        TypedQuery<Politico> query = em.createNamedQuery("Politico.PorNome", Politico.class);
        query.setParameter("nome", "Politico o gamer");
        Politico politico = query.getSingleResult();
        assertNotNull(politico);
        politico.setNome("Potilico sonysta");
        em.clear();       
        em.merge(politico);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
    
    @Test
    public void deletarPolitico(){
        
        TypedQuery<Politico> query = em.createNamedQuery("Politico.PorNome", Politico.class);
        query.setParameter("nome", "Politico o noob");
        Politico politico = query.getSingleResult();
        assertNotNull(politico);
        em.remove(politico);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
}
