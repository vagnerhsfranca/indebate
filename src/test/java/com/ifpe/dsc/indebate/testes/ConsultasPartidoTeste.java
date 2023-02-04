/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Partido;
import jakarta.persistence.TypedQuery;
import java.util.Arrays;
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
    }
    
    @Test
    public void consultarPartidoSemMembros() {

        TypedQuery<Partido> query = em.createQuery("SELECT p FROM Partido p WHERE p.membros IS EMPTY", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.get(0).getMembros().isEmpty());
    }
    
    @Test
    public void consultarPartidoInnerJoinPolitico() {

        TypedQuery<String> query = em.createQuery("SELECT DISTINCT ptd.nome "
                + "FROM Politico p "
                + "INNER JOIN p.partido ptd "
                + "WHERE p.cargo = 'Acessor'", String.class);
        List<String> partidos = query.getResultList();
        assertTrue(partidos.contains("Partido Da Galera"));
    }
    
    @Test
    public void consultarPartidoLeftJoin() {

        TypedQuery<Object[]> query = em.createQuery("SELECT DISTINCT p.nome, pol.nome "
                + "FROM Partido p "
                + "LEFT OUTER JOIN p.membros pol "
                + "ORDER BY p.nome", Object[].class);
        List<Object[]> partidos = query.getResultList();
        assertTrue(Arrays.toString((Object[]) partidos.toArray()[0]).equals("[Partido Da Cidade, Outro Politico da Cidade]"));    
    }
    
    @Test
    public void consultarPartidoLeftJoinNull() {

        TypedQuery<Object[]> query = em.createQuery("SELECT DISTINCT p.nome, pol.nome "
                + "FROM Partido p "
                + "LEFT OUTER JOIN p.membros pol "
                + "ORDER BY p.nome", Object[].class);
        List<Object[]> partidos = query.getResultList();
        System.out.println( "RESULT HERE>>> " + Arrays.toString((Object[]) partidos.toArray()[19]));
        assertTrue(Arrays.toString((Object[]) partidos.toArray()[19]).equalsIgnoreCase("[Partido dos kids, null]"));
    }
    
    @Test
    public void consultarPartidoJoinFetchMembro() {

        TypedQuery<Partido> query = em.createQuery("SELECT DISTINCT p "
                + "FROM Partido p "
                + "JOIN FETCH p.membros "
                + "ORDER BY p.nome", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.get(0).getMembros().get(0).getNome().equalsIgnoreCase("Politico da Cidade"));
    }
    
    @Test
    public void consultarPartidoSubstringLength (){

        TypedQuery<Partido> query = em.createQuery("SELECT DISTINCT p "
                + "FROM Partido p "
                + "WHERE SUBSTRING(p.nome, 11, LENGTH(p.nome)) LIKE '%vo%' "
                + "ORDER BY p.nome", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.size() == 2);
    }
    
    @Test
    public void consultarPartidoLikeTrimBoth() {

        TypedQuery<Partido> query = em.createQuery("SELECT DISTINCT p "
                + "FROM Partido p "
                + "WHERE p.nome LIKE TRIM(BOTH  '*' FROM '***%Do%****') "
                + "ORDER BY p.nome", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.size() == 3);
    }
    
    @Test
    public void consultarPartidoLikeTrimLeading() {

        TypedQuery<Partido> query = em.createQuery("SELECT DISTINCT p "
                + "FROM Partido p "
                + "WHERE p.nome LIKE TRIM(LEADING  '#' FROM '####%Da%') "
                + "ORDER BY p.nome", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.size() == 2);
    }
    
    @Test
    public void consultarPartidoMaxSizeMembros() {

        TypedQuery<Partido> query = em.createQuery("SELECT p "
                + "FROM Partido p "
                + "WHERE SIZE(p.membros) >= "
                + "(SELECT MAX(SIZE(p.membros)) FROM Partido p)", Partido.class);
        List<Partido> partidos = query.getResultList();
        assertTrue(partidos.get(0).getNome().equalsIgnoreCase("Partido da Cidade"));
    }
}
