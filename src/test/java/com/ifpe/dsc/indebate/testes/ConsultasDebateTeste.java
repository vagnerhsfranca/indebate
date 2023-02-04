package com.ifpe.dsc.indebate.testes;

import com.ifpe.dsc.indebate.model.Debate;
import com.ifpe.dsc.indebate.model.Endereco;
import com.ifpe.dsc.indebate.model.Pessoa;
import com.ifpe.dsc.indebate.model.Politico;
import jakarta.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Guilherme Oliveira
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
        
    }
    
    @Test
    public void consultarDistinctTitulo(){
        
        TypedQuery<String> query = em.createQuery("SELECT distinct(d.titulo) FROM Debate d", String.class);
        List<String> debates = query.getResultList();
        assertTrue(debates.size() == 7);
        
    }
    
    @Test
    public void consultarOrderByTitulo(){
        
        TypedQuery<String> query = em.createQuery("SELECT distinct(d.titulo) as t FROM Debate d order by t", String.class);
        List<String> debates = query.getResultList();
        assertTrue(debates.size() == 7);
        
        
         assertTrue(debates.get(0).equals("Agro é pop"));
        
    }
    
    @Test
    public void consultarOrderByDescricao(){
        
        TypedQuery<String> query = em.createQuery("SELECT distinct(d.descricao) as des FROM Debate d order by des", String.class);
        List<String> debates = query.getResultList();
        assertTrue(debates.size() == 7);
        
        
         assertTrue(debates.get(0).equals("A melhor IDE para desenvolvimento em java"));
        
    }
    
    @Test
    public void consultarCount(){
        
        TypedQuery<Long> query = em.createQuery("SELECT count(d) FROM Debate d", Long.class);
        Long count = query.getSingleResult();
        assertTrue(count == 7);
        
    }
    
    @Test
    public void consultarIsNotNull(){
        
        TypedQuery<Long> query = em.createQuery("SELECT count(d) FROM Debate d WHERE d.titulo is not null", Long.class);
        Long count = query.getSingleResult();
        assertTrue(count == 7);
        
    }
    
   @Test
    public void consultarIsNull(){
        
        TypedQuery<Long> query = em.createQuery("SELECT count(d) FROM Debate d WHERE d.titulo is null", Long.class);
        Long count = query.getSingleResult();
        assertTrue(count == 0);
        
    }
    
    @Test
    public void consultarMinData(){
        
        String dateString = "Mon Jan 10 00:00:00 BRT 2022";
        
        TypedQuery<Date> query = em.createQuery("SELECT min(d.data) FROM Debate d", Date.class);
        Date data = query.getSingleResult();
        assertTrue(dateString.equals(data.toString()));
        
    }
    
    @Test
    public void consultarMaxData(){
        
        String dateString = "Tue Jan 10 00:00:00 BRT 2023";
        
        TypedQuery<Date> query = em.createQuery("SELECT max(d.data) FROM Debate d", Date.class);
        Date data = query.getSingleResult();
        assertTrue(dateString.equals(data.toString()));
        
    }
    
    @Test
    public void consultarAvgCapacidade(){
        
        TypedQuery<Double> query = em.createQuery("SELECT avg(d.capacidade) FROM Debate d", Double.class);
        Double mediaCapacidade = query.getSingleResult();
        assertNotNull(mediaCapacidade);
        
    }
    
    @Test
    public void consultarSumCapacidade(){
        
        TypedQuery<Long> query = em.createQuery("SELECT sum(d.capacidade) FROM Debate d", Long.class);
        Long sumCapacidade = query.getSingleResult();
        assertNotNull(sumCapacidade);
        
    }
    
    @Test
    public void consultarSizeParticipantes(){
        
        TypedQuery<Debate> queryDebate = em.createQuery("SELECT d FROM Debate d WHERE d.id = 1", Debate.class);
        Debate debate = queryDebate.getSingleResult();
        
        TypedQuery<Integer> query = em.createQuery("SELECT size(d.participantes) FROM Debate d WHERE d.id = 1", Integer.class);
        Integer sizeParticipantes = query.getSingleResult();
        assertNotNull(sizeParticipantes);
        assertTrue(sizeParticipantes == debate.getParticipantes().size());
        
    }
    
    @Test
    public void consultarSizeReportes(){
        
        TypedQuery<Debate> queryDebate = em.createQuery("SELECT d FROM Debate d WHERE d.id = 1", Debate.class);
        Debate debate = queryDebate.getSingleResult();
        
        TypedQuery<Integer> query = em.createQuery("SELECT size(d.reportes) FROM Debate d WHERE d.id = 1", Integer.class);
        Integer sizeParticipantes = query.getSingleResult();
        assertNotNull(sizeParticipantes);
        assertTrue(sizeParticipantes == debate.getReportes().size());
        
    }
    
    @Test
    public void consultarConcat(){
        
        TypedQuery<Debate> queryDebate = em.createQuery("SELECT d FROM Debate d WHERE d.id = 1", Debate.class);
        Debate debate = queryDebate.getSingleResult();
        
        TypedQuery<String> query = em.createQuery("SELECT concat(d.titulo, d.descricao) FROM Debate d WHERE d.id = 1", String.class);
        String concat = query.getSingleResult();
        assertNotNull(concat);
        assertTrue(concat.equals(debate.getTitulo().concat(debate.getDescricao())));
        
    }
    
    @Test
    public void consultarSubString(){
        
        TypedQuery<Debate> queryDebate = em.createQuery("SELECT d FROM Debate d WHERE d.id = 1", Debate.class);
        Debate debate = queryDebate.getSingleResult();
        
        TypedQuery<String> query = em.createQuery("SELECT substring(d.titulo, 1, 4) FROM Debate d WHERE d.id = 1", String.class);
        String subString = query.getSingleResult();
        assertNotNull(subString);
        assertTrue(subString.equals(debate.getTitulo().substring(0, 4)));
        
    }
    
    @Test
    public void consultarLengthTitulo(){
        
        TypedQuery<Debate> queryDebate = em.createQuery("SELECT d FROM Debate d WHERE d.id = 1", Debate.class);
        Debate debate = queryDebate.getSingleResult();
        
        TypedQuery<Integer> query = em.createQuery("SELECT LENGTH(d.titulo) FROM Debate d WHERE d.id = 1", Integer.class);
        Integer length = query.getSingleResult();
        assertNotNull(length);
        assertTrue(length == debate.getTitulo().length());
        
    }
    
    @Test
    public void consultarLengthDescricao(){
        
        TypedQuery<Debate> queryDebate = em.createQuery("SELECT d FROM Debate d WHERE d.id = 1", Debate.class);
        Debate debate = queryDebate.getSingleResult();
        
        TypedQuery<Integer> query = em.createQuery("SELECT LENGTH(d.descricao) FROM Debate d WHERE d.id = 1", Integer.class);
        Integer length = query.getSingleResult();
        assertNotNull(length);
        assertTrue(length == debate.getDescricao().length());
        
    }
    
    @Test
    public void consultarBetween(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.data between '2022-12-12' AND '2023-12-12'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 2);
        
    }
    
    @Test
    public void consultarBetween2(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.data between '2022-01-10' AND '2023-12-12'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 7);
        
    }
    
    @Test
    public void consultarComIn(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.titulo in ('Agro é pop','Tecnologia')", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 2);
        
        debates.forEach(debate -> 
         assertTrue(debate.getTitulo().equals("Agro é pop") || debate.getTitulo().equals("Tecnologia"))
        );
        
    }
    
    @Test
    public void consultarComIn2(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.titulo in ('Crescimento do agro no Brasil','Eclipse te amo')", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 2);
        
        debates.forEach(debate -> 
         assertTrue(debate.getTitulo().equals("Eclipse te amo") || debate.getTitulo().equals("Crescimento do agro no Brasil"))
        );
        
    }
    
    @Test
    public void consultarComOr(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.titulo = 'Agro é pop' OR d.descricao = 'A melhor IDE para desenvolvimento em java'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 2);
        
        debates.forEach(debate -> 
         assertTrue(debate.getTitulo().equals("Agro é pop") || debate.getDescricao().equals("A melhor IDE para desenvolvimento em java"))
        );
        
    }
    
    @Test
    public void consultarIsEmpty(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.participantes IS EMPTY", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 1);
        
        debates.forEach(debate -> 
         assertTrue(debate.getTitulo().equals("Netbeans em projeto real?"))
        );
        
    }
    
    @Test
    public void consultarIsEmpty2(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.reportes IS EMPTY", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 0);
        
    }
    
    @Test
    public void consultarIsNotEmpty(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.participantes IS NOT EMPTY", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 6);
        
        debates.forEach(debate -> 
         assertTrue(!debate.getTitulo().equals("Netbeans em projeto real?"))
        );
        
    }
    
    @Test
    public void consultarIsNotEmpty2(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.reportes IS NOT EMPTY", Debate.class);
        List<Debate> debates = query.getResultList();
        assertNotNull(debates);
        assertTrue(debates.size() == 7);
        
    }
    
    
    
    @Test
    public void consultarLike1(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.titulo like 'Netbeans em ambiente real'", Debate.class);
        Debate debate = query.getSingleResult();
        assertTrue(debate.getTitulo().equals("Netbeans em ambiente real"));
        
    }
    
    @Test
    public void consultarLike2(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.titulo like 'Netbeans %'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertTrue(debates.size() == 2);

        
    }
    
    @Test
    public void consultarLike3(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d WHERE d.titulo like '%Netbeans%'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertTrue(debates.size() == 2);
        
    }
    
    @Test
    public void consultarInnerJoin1(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d INNER JOIN Pessoa p on d.mediador.id = p.id WHERE p.nome = 'Jão Pedro'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertTrue(debates.size() == 3);

        
    }
    
    @Test
    public void consultarInnerJoin2(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d INNER JOIN Pessoa p on d.mediador.id = p.id WHERE p.nome != 'Jão Pedro'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertTrue(debates.size() == 4);

        
    }

    @Test
    public void consultarInnerJoinComLike(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d INNER JOIN Pessoa p on d.mediador.id = p.id WHERE p.nome like '%Pedro%'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertTrue(debates.size() == 7);
   
    }
    
    @Test
    public void consultarInnerJoinComLikeComCount(){
        
        TypedQuery<java.lang.Long> query = em.createQuery("SELECT count(d) FROM Debate d INNER JOIN Pessoa p on d.mediador.id = p.id WHERE p.nome like '%Pedro%'", java.lang.Long.class);
        java.lang.Long count = query.getSingleResult();
        assertTrue(count == 7l);
        
    }
    
    @Test
    public void consultarLeftJoin1(){
        
        TypedQuery<Debate> query = em.createQuery("SELECT d FROM Debate d LEFT JOIN Pessoa p on d.mediador.id = p.id WHERE p.nome != 'Jão Pedro'", Debate.class);
        List<Debate> debates = query.getResultList();
        assertTrue(debates.size() == 4);
        
    }
    
    
}
