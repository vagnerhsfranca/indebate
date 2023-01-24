
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.dsc.indebate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vagner França
 */
@Entity
@Table(name = "tb_partido")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Partido.PorNome",
                    query = "SELECT p FROM Partido p WHERE p.nome = :nome"
            )
        }
)
public class Partido implements Serializable{

    @Id
    @Column(name = "partido_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "partido_txt_nome")
    private String nome;
    @OneToMany(mappedBy = "partido")
    private List<Politico> membros;
    @OneToOne @JoinColumn(name = "politico_presidente_id", nullable = true, referencedColumnName = "pessoa_id")
    private Politico presidente;

    public int getId() {
        return id;
    }

    public Politico getPresidente() {
        return presidente;
    }

    public void setPresidente(Politico presidente) {
        this.presidente = presidente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Politico> getMembros() {
        return membros;
    }

    public void setMembros(List<Politico> membros) {
        this.membros = membros;
    }

    @Override
    public String toString() {
        return "Partido{" + "id=" + id + ", nome=" + nome + ", membros=" + membros + '}';
    }

}
