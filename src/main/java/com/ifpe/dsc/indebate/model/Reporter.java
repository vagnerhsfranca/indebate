/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.dsc.indebate.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.List;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Guilherme Oliveira
 */
@Entity
@Table (name = "tb_reporter")
@DiscriminatorValue(value = "REP")
@PrimaryKeyJoinColumn(name="pessoa_id", referencedColumnName = "pessoa_id")

@NamedQueries(
        {
            @NamedQuery(
                    name = "Reporter.PorNome",
                    query = "SELECT r FROM Reporter r WHERE r.nome = :nome"
            )
        }
)
public class Reporter extends Pessoa implements Serializable{

    @Column (name = "reporter_txt_jornal")
    private String jornal;
    
    @ManyToMany(mappedBy = "reportes")
    private List<Debate> debates;

    public String getJornal() {
        return jornal;
    }

    public void setJornal(String jornal) {
        this.jornal = jornal;
    }

    public List<Debate> getDebates() {
        return debates;
    }

    public void setDebates(List<Debate> debates) {
        this.debates = debates;
    }
}
