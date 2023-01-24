/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.dsc.indebate.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Guilherme Oliveira
 */
@Entity @Table (name = "tb_debate")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Debate.PorTitulo",
                    query = "SELECT d FROM Debate d WHERE d.titulo = :titulo"
            )
        }
)

public class Debate implements Serializable{
    
    @Id  
    @Column (name = "debate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne @JoinColumn(name = "pessoa_mediador_id", referencedColumnName = "pessoa_id", nullable = true)
    private Pessoa mediador;
    @Column (name="debate_txt_titulo")
    private String titulo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @ManyToMany @JoinTable(
        name = "debate_participantes",
        joinColumns = @JoinColumn(name = "debate_id", referencedColumnName = "debate_id"),
        inverseJoinColumns = @JoinColumn(name = "politico_participante_id", referencedColumnName = "pessoa_id")
    )
    private List <Politico> participantes;
    
    @ElementCollection @CollectionTable(name = "debate_categorias", 
            joinColumns = @JoinColumn(name = "debate_id", referencedColumnName = "debate_id")) 
    @Column (name = "debate_txt_categorias")
    private List<String> categorias;
    
    @Embedded
    protected Endereco endereco;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getMediador() {
        return mediador;
    }

    public void setMediador(Pessoa mediador) {
        this.mediador = mediador;
    }

    public List<Politico> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Politico> participantes) {
        this.participantes = participantes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Debate{" + "id=" + id + ", mediador=" + mediador + ", titulo=" + titulo + ", data=" + data + ", participantes=" + participantes + ", categorias=" + categorias + ", endereco=" + endereco + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.mediador);
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.data);
        hash = 97 * hash + Objects.hashCode(this.participantes);
        hash = 97 * hash + Objects.hashCode(this.categorias);
        hash = 97 * hash + Objects.hashCode(this.endereco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Debate other = (Debate) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.mediador, other.mediador)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.participantes, other.participantes)) {
            return false;
        }
        if (!Objects.equals(this.categorias, other.categorias)) {
            return false;
        }
        return Objects.equals(this.endereco, other.endereco);
    }
    
    
}
