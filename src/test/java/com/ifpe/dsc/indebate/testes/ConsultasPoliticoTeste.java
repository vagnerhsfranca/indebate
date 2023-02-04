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
    }
    
    @Test
    public void ConsultarPorCargo() {
        TypedQuery<Politico> query = em.createNamedQuery("Politico.PorCargo", Politico.class);
        query.setParameter("cargo", "Dep%");
        List<Politico> politicos = query.getResultList();
         assertEquals(6, politicos.size());
    }
    
    @Test
    public void ConsultarCargosDistintos() {
        TypedQuery<String> query = em.createNamedQuery("Politico.CargosDistintos", String.class);
        List<String> cargos = query.getResultList();
        assertTrue(cargos.contains("Acessor"));
    }
    
    @Test
    public void ConsultarVereadorAcessor() {
        TypedQuery<Long> query = em.createNamedQuery("Politico.VereadorOuAcessor", Long.class);
        Long quantidade = query.getSingleResult();
        assertTrue(quantidade == 5);
    }
    
    public void ConsultarPoliticosComDebates(){
        TypedQuery<Politico> query = em.createQuery("SELECT p FROM Politico p WHERE p.dabates IS NOT NULL", Politico.class);
        List<Politico> politicos = query.getResultList();
        assertFalse(politicos.isEmpty());
    }
        
    @Test
    public void consultarPoliticosDeUmDebate() {

        Debate debate = em.find(Debate.class, 1);
        TypedQuery<Politico> query = em.createQuery("SELECT p FROM Politico p WHERE :debate MEMBER OF p.debates", Politico.class);
        query.setParameter("debate", debate);
        List<Politico> politicos = query.getResultList();
        assertTrue(politicos.get(0).getDebates().contains(debate));
    }
    
    @Test
    public void consultarPoliticoPartidoMaisMembros() {

        TypedQuery<Politico> query = em.createQuery("SELECT pol "
            + "FROM Politico pol "
            + "WHERE pol.partido.nome = "
            + "(SELECT p.nome FROM Partido p "
            + "WHERE SIZE(p.membros) >= (SELECT MAX(SIZE(p.membros)) FROM Partido p))", Politico.class);
        List<Politico> politicos = query.getResultList();
            assertTrue(politicos.size() == 6);
    }
    
    @Test
    public void consultarPoliticoBetween() {

        TypedQuery<Politico> query = em.createQuery("SELECT pol "
            + "FROM Politico pol "
            + "WHERE pol.idPessoa BETWEEN 1 AND 5 ", Politico.class);
        List<Politico> politicos = query.getResultList();
        assertTrue(politicos.get(4).getNome().equalsIgnoreCase("Politico do Velho"));
    }
    
    @Test
    public void consultarTotalDebatesPoliticos() {
        TypedQuery<Long> query = em.createQuery("SELECT SUM(SIZE(pol.debates)) "
            + "FROM Politico pol ", Long.class);
        List<Long> totalDebates = query.getResultList();
        
        System.out.println("OLHA A LISTAAAAAAAAAA" + totalDebates);
        assertTrue(totalDebates.get(0) == 34);
    }
    
    @Test
    public void ConsultarPoliticosSemDebates(){
        TypedQuery<Politico> query = em.createQuery("SELECT pol "
            + "FROM Politico pol "
            + "WHERE pol.debates IS EMPTY", Politico.class);
        List<Politico> politicos = query.getResultList();
        assertTrue(politicos.get(politicos.size() - 1).getNome().equalsIgnoreCase("Politico o noob"));
    }
    
    @Test
    public void consultarPoliticosNaoPresidentes(){
        TypedQuery<Politico> query = em.createQuery("SELECT pol "
                + "FROM Politico pol "
                + "WHERE pol.cargo NOT LIKE :cargo", Politico.class);
        query.setParameter("cargo", "%sident%");
        List<Politico> politicos = query.getResultList();
        politicos.forEach(p->assertTrue(!p.getCargo().equalsIgnoreCase("Presidente")));
    }
    
    
}
