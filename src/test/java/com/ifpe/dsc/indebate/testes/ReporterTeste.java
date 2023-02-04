/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Partido;
import com.ifpe.dsc.indebate.model.Politico;
import com.ifpe.dsc.indebate.model.Reporter;
import jakarta.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author guilh
 */
public class ReporterTeste extends Teste{
    
    @Test
    public void persistirReporter() {
        
        Reporter reporter = new Reporter();
        
        reporter.setNome("Jãozim Levado");
        
        em.persist(reporter);
        em.flush();
        
        assertNotNull(reporter.getIdPessoa());

    }
    
    @Test
    public void atualizarReporter(){
        
        TypedQuery<Reporter> query = em.createNamedQuery("Reporter.PorNome", Reporter.class);
        query.setParameter("nome", "Amelia mel");
        Reporter reporter = query.getSingleResult();
        assertNotNull(reporter);
        reporter.setNome("Juju beleza");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Juju beleza");
        reporter = query.getSingleResult();
        assertNotNull(reporter);
        
    }
    
    @Test
    public void atualizarReporterMerge(){

        TypedQuery<Reporter> query = em.createNamedQuery("Reporter.PorNome", Reporter.class);
        query.setParameter("nome", "Beatriz carla");
        Reporter reporter = query.getSingleResult();
        assertNotNull(reporter);
        reporter.setNome("Beatriz almeida");
        em.clear();       
        em.merge(reporter);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
    
    @Test
    public void deletarReporter(){
        
        TypedQuery<Reporter> query = em.createNamedQuery("Reporter.PorNome", Reporter.class);
        query.setParameter("nome", "Abestada toda");
        Reporter reporter = query.getSingleResult();
        assertNotNull(reporter);
        em.remove(reporter);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
    
}
