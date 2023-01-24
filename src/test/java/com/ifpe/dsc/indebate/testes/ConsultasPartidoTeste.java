/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Partido;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Vagner França
 */
public class ConsultasPartidoTeste extends Teste{
    
    @Test
    public void consultaNomePartidoToUpper() {

        TypedQuery<String> query = em.createQuery("SELECT UPPER(p.nome) FROM Partido p WHERE p.id = 1", String.class);
        List<String> partido = query.getResultList();
        assertTrue(partido.get(0).equals("PARTIDO DO POVO"));
        em.clear();
    }
    
    @Test
    public void consultarPartidoSemMembros() {

        TypedQuery<Partido> query = em.createQuery("SELECT p FROM Partido p WHERE p.membros IS EMPTY", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.get(0).getMembros().isEmpty());
        em.clear();
    }
}
