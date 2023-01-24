/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Endereco;
import jakarta.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Vagner França
 */
public class ConsultasDebateTeste extends Teste{
    
    @Test
    public void consultarDebate() {
        
        TypedQuery<Debate> query = em.createNamedQuery("Debate.PorTitulo", Debate.class);
        query.setParameter("titulo", "Agro é pop");
        Debate debate = query.getSingleResult();
        
        assertNotNull(debate);
        assertEquals("Agro é pop", debate.getTitulo());

        Endereco endereco = debate.getEndereco();
        assertNotNull(endereco);
        assertEquals("Pernambuco", endereco.getEstado());
        assertEquals("50030-230", endereco.getCep());
        assertEquals("Santo Amaro", endereco.getBairro());
        assertEquals("R. da Aurora", endereco.getLogradouro());
        
        em.clear();
        
    }
}
