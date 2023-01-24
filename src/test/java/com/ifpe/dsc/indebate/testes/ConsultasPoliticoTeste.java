/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Politico;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Vagner França
 */
public class ConsultasPoliticoTeste extends Teste{
    
    @Test
    public void ConsultarPorPartido() {
        
        TypedQuery<Politico> query = em.createNamedQuery("Politico.PorPartido", Politico.class);
        query.setParameter("partido", "Partido Rural");
        List<Politico> politicos = query.getResultList();
        
        assertEquals(3, politicos.size());
        em.clear();
    }
    
    @Test
    public void ConsultarPorCargo() {
        TypedQuery<Politico> query = em.createNamedQuery("Politico.PorCargo", Politico.class);
        query.setParameter("cargo", "Dep%");
        List<Politico> politicos = query.getResultList();
        assertEquals(6, politicos.size());
        em.clear();
    }
    
    @Test
    public void ConsultarCargosDistintos() {
        TypedQuery<String> query = em.createNamedQuery("Politico.CargosDistintos", String.class);
        List<String> cargos = query.getResultList();
        assertTrue(cargos.contains("Acessor"));
        em.clear();
    }
    
    @Test
    public void ConsultarVereadorAcessor() {
        TypedQuery<Long> query = em.createNamedQuery("Politico.VereadorOuAcessor", Long.class);
        Long quantidade = query.getSingleResult();
        assertTrue(quantidade == 5);
        em.clear();
    }
    
    public void ConsultarPoliticosComDebates(){
        TypedQuery<Politico> query = em.createQuery("SELECT p FROM Politico p WHERE p.dabates IS NOT NULL", Politico.class);
        List<Politico> politicos = query.getResultList();
        assertFalse(politicos.isEmpty());
        em.clear();
    }
        
    @Test
    public void consultarPoliticosDeUmDebate() {

        Debate debate = em.find(Debate.class, 1);
        TypedQuery<Politico> query = em.createQuery("SELECT p FROM Politico p WHERE :debate MEMBER OF p.debates", Politico.class);
        query.setParameter("debate", debate);
        List<Politico> politicos = query.getResultList();
        assertTrue(politicos.get(0).getDebates().contains(debate));
        em.clear();
    }
}
