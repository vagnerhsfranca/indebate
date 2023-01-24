/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Endereco;
import com.ifpe.dsc.indebate.model.Partido;
import java.util.Arrays;
import java.util.Calendar;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guilh
 */
public class DebateTeste extends Teste{
    
    @Test
    public void persistirDebate() {
        
        Debate debate = new Debate();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.DECEMBER, 24, 13, 0, 0);
        debate.setData(calendar.getTime());
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Várzea");
        endereco.setCep("50770-340");
        endereco.setLogradouro("Avenida Professor Moraes Rego");
        endereco.setEstado("Pernambuco");
        endereco.setNumero(40);
        endereco.setCidade("Recife");
        
        debate.setEndereco(endereco);
        debate.setTitulo("Eclipse é melhor que netbeans");
        debate.setCategorias(Arrays.asList("saude", "educação"));
        
        em.persist(debate);
        em.flush();
        
        assertNotNull(debate.getId());

    }
    
    @Test
    public void atualizarDebate(){
        
        TypedQuery<Debate> query = em.createNamedQuery("Debate.PorTitulo", Debate.class);
        query.setParameter("titulo", "Agro é pop");
        Debate debate = query.getSingleResult();
        assertNotNull(debate);
        debate.setTitulo("Melhores formas de trabalhar com agro");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("titulo", "Melhores formas de trabalhar com agro");
        debate = query.getSingleResult();
        assertNotNull(debate);
        
    }
    
    @Test
    public void atualizarDebateMerge(){

        TypedQuery<Debate> query = em.createNamedQuery("Debate.PorTitulo", Debate.class);
        query.setParameter("titulo", "Crescimento do agro no Brasil");
        Debate debate = query.getSingleResult();
        assertNotNull(debate);
        debate.setTitulo("Dados sobre o agro no Brasil");
        em.clear();       
        em.merge(debate);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
    
    @Test
    public void deletarDebate(){
        
        TypedQuery<Debate> query = em.createNamedQuery("Debate.PorTitulo", Debate.class);
        query.setParameter("titulo", "Tecnologia");
        Debate debate = query.getSingleResult();
        assertNotNull(debate);
        em.remove(debate);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
    }
    
}
