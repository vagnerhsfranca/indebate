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
 * @author Vagner França
 */
@Entity
@Table (name = "tb_politico")
@DiscriminatorValue(value = "POL")
@PrimaryKeyJoinColumn(name="pessoa_id", referencedColumnName = "pessoa_id")

@NamedQueries(
        {
            @NamedQuery(
                    name = "Politico.PorPartido",
                    query = "SELECT p FROM Politico p WHERE p.partido.nome = :partido"
            ),
            @NamedQuery(
                    name = "Politico.PorNome",
                    query = "SELECT p FROM Politico p WHERE p.nome = :nome"
            ),
            @NamedQuery(
                    name = "Politico.PorCargo",
                    query = "SELECT p FROM Politico p WHERE p.cargo LIKE :cargo ORDER BY p.nome"
            ),
            @NamedQuery(
                    name = "Politico.CargosDistintos",
                    query = "SELECT DISTINCT (p.cargo) FROM Politico p ORDER BY p.cargo"
            ),
            @NamedQuery(
                    name = "Politico.VereadorOuAcessor",
                    query = "SELECT COUNT(p) FROM Politico p WHERE p.cargo IN ('Vereador', 'Acessor')"
            )
        }
)

public class Politico extends Pessoa implements Serializable{
    @ManyToOne @JoinColumn(name = "partido_id", referencedColumnName = "partido_id")
    private Partido partido;
    @ManyToMany(mappedBy = "participantes")
    private List<Debate> debates;
    @Column (name = "politico_txt_cargo")
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public List<Debate> getDebates() {
        return debates;
    }

    public void setDebates(List<Debate> debates) {
        this.debates = debates;
    }
}
