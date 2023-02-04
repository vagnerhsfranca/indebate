/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Endereco;
import com.ifpe.dsc.indebate.model.Reporter;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author guilh
 */
public class ConsultasReporterTeste extends Teste{
    
    @Test
    public void consultarDebate() {
        
        TypedQuery<Reporter> query = em.createNamedQuery("Reporter.PorNome", Reporter.class);
        query.setParameter("nome", "Victor Henrique");
        Reporter reporter = query.getSingleResult();
        
        assertNotNull(reporter);
        assertEquals("Victor Henrique", reporter.getNome());
        assertEquals("Band", reporter.getJornal());
        
    }
    
    @Test
    public void consultarDistinctNome(){
        
        TypedQuery<String> query = em.createQuery("SELECT distinct(r.nome) FROM Reporter r", String.class);
        List<String> nomes = query.getResultList();
        assertTrue(nomes.size() == 8);
        
    }
    
    @Test
    public void consultarOrderByJornal(){
        
        TypedQuery<Reporter> query = em.createQuery("SELECT r FROM Reporter r order by r.jornal", Reporter.class);
        List<Reporter> reporter = query.getResultList();
        assertTrue(reporter.size() == 8);
        
         assertTrue(reporter.get(0).getJornal().equals("Band"));
        
    }
    
    @Test
    public void consultarOrderByNome(){
        
        TypedQuery<Reporter> query = em.createQuery("SELECT r FROM Reporter r order by r.nome", Reporter.class);
        List<Reporter> reporter = query.getResultList();
        assertTrue(reporter.size() == 8);
        
         assertTrue(reporter.get(0).getNome().equals("Abestada toda"));
        
    }
    
    @Test
    public void consultarCount(){
        
        TypedQuery<Long> query = em.createQuery("SELECT count(r) FROM Reporter r", Long.class);
        Long count = query.getSingleResult();
        assertTrue(count == 8);
        
    }
    
    @Test
    public void consultarIsNotNull(){
        
        TypedQuery<Long> query = em.createQuery("SELECT count(r) FROM Reporter r WHERE r.jornal is not null", Long.class);
        Long count = query.getSingleResult();
        assertTrue(count == 8);
        
    }
    
    @Test
    public void consultarIsNull(){
        
        TypedQuery<Long> query = em.createQuery("SELECT count(r) FROM Reporter r WHERE r.nome is null", Long.class);
        Long count = query.getSingleResult();
        assertTrue(count == 0);
        
    }
    
    @Test
    public void consultarConcat(){
        
        TypedQuery<Reporter> queryReporter = em.createQuery("SELECT r FROM Reporter r WHERE r.idPessoa = 18", Reporter.class);
        Reporter reporter = queryReporter.getSingleResult();
        
        TypedQuery<String> query = em.createQuery("SELECT concat(r.nome, r.jornal) FROM Reporter r WHERE r.idPessoa = 18", String.class);
        String concat = query.getSingleResult();
        assertNotNull(concat);
        assertTrue(concat.equals(reporter.getNome().concat(reporter.getJornal())));
        
    }
    
    @Test
    public void consultarSubString(){
        
        TypedQuery<Reporter> queryDebate = em.createQuery("SELECT r FROM Reporter r WHERE r.idPessoa = 18", Reporter.class);
        Reporter reporter = queryDebate.getSingleResult();
        
        TypedQuery<String> query = em.createQuery("SELECT substring(r.jornal, 1, 3) FROM Reporter r WHERE r.idPessoa = 18", String.class);
        String subString = query.getSingleResult();
        assertNotNull(subString);
        assertTrue(subString.equals(reporter.getJornal().substring(0, 3)));
        
    }
    
    @Test
    public void consultarLengthJornal(){
        
        TypedQuery<Reporter> queryDebate = em.createQuery("SELECT r FROM Reporter r WHERE r.idPessoa = 18", Reporter.class);
        Reporter reporter = queryDebate.getSingleResult();
        
        TypedQuery<Integer> query = em.createQuery("SELECT LENGTH(r.jornal) FROM Reporter r WHERE r.idPessoa = 18", Integer.class);
        Integer length = query.getSingleResult();
        assertNotNull(length);
        assertTrue(length == reporter.getJornal().length());
        
    }
    
    @Test
    public void consultarLengthNome(){
        
        TypedQuery<Reporter> queryDebate = em.createQuery("SELECT r FROM Reporter r WHERE r.idPessoa = 18", Reporter.class);
        Reporter reporter = queryDebate.getSingleResult();
        
        TypedQuery<Integer> query = em.createQuery("SELECT LENGTH(r.nome) FROM Reporter r WHERE r.idPessoa = 18", Integer.class);
        Integer length = query.getSingleResult();
        assertNotNull(length);
        assertTrue(length == reporter.getNome().length());
        
    }
    
    @Test
    public void consultarUpperCaseNome(){
        
        TypedQuery<Reporter> queryDebate = em.createQuery("SELECT r FROM Reporter r WHERE r.idPessoa = 18", Reporter.class);
        Reporter reporter = queryDebate.getSingleResult();
        
        TypedQuery<String> query = em.createQuery("SELECT UPPER(r.nome) FROM Reporter r WHERE r.idPessoa = 18", String.class);
        String upper = query.getSingleResult();
        assertNotNull(upper);
        assertTrue(upper.equals(reporter.getNome().toUpperCase()));
        
    }
    
    @Test
    public void consultarLowerCaseNome(){
        
        TypedQuery<Reporter> queryDebate = em.createQuery("SELECT r FROM Reporter r WHERE r.idPessoa = 18", Reporter.class);
        Reporter reporter = queryDebate.getSingleResult();
        
        TypedQuery<String> query = em.createQuery("SELECT LOWER(r.nome) FROM Reporter r WHERE r.idPessoa = 18", String.class);
        String upper = query.getSingleResult();
        assertNotNull(upper);
        assertTrue(upper.equals(reporter.getNome().toLowerCase()));
        
    }
    
    @Test
    public void consultarComIn(){
        
        TypedQuery<Reporter> query = em.createQuery("SELECT r FROM Reporter r WHERE r.nome in ('Pedro João','Gustavo Pedro')", Reporter.class);
        List<Reporter> reporter = query.getResultList();
        assertNotNull(reporter);
        assertTrue(reporter.size() == 2);
        
        reporter.forEach(r -> 
         assertTrue(r.getNome().equals("Pedro João") || r.getNome().equals("Gustavo Pedro"))
        );
        
    }
    
    @Test
    public void consultarComOr(){
        
        TypedQuery<Reporter> query = em.createQuery("SELECT r FROM Reporter r WHERE r.nome = 'Pedro João' or r.nome = 'Gustavo Pedro'", Reporter.class);
        List<Reporter> reporter = query.getResultList();
        assertNotNull(reporter);
        assertTrue(reporter.size() == 2);
        
        reporter.forEach(r -> 
         assertTrue(r.getNome().equals("Pedro João") || r.getNome().equals("Gustavo Pedro"))
        );
        
    }
    
    @Test
    public void consultarLike(){
        
        TypedQuery<Reporter> query = em.createQuery("SELECT r FROM Reporter r WHERE r.nome like '%Amelia%'", Reporter.class);
        Reporter reporter = query.getSingleResult();
        assertTrue(reporter.getNome().equals("Amelia mel"));
        
    }
    
    @Test
    public void consultarMaxJornal(){
        
        String jornalMax = "SBT";
        
        TypedQuery<String> query = em.createQuery("SELECT max(r.jornal) FROM Reporter r", String.class);
        String jornal = query.getSingleResult();
        assertTrue(jornalMax.equals(jornal));
        
    }
    
    @Test
    public void consultarMinJornal(){
        
        String jornalMin = "Band";
        
        TypedQuery<String> query = em.createQuery("SELECT min(r.jornal) FROM Reporter r", String.class);
        String jornal = query.getSingleResult();
        assertTrue(jornalMin.equals(jornal));
        
    }
}
