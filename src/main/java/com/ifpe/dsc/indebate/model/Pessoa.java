/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.dsc.indebate.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Vagner França
 */
@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "pessoa_disc", //Nome da coluna que vai discriminar subclasses.
        discriminatorType = DiscriminatorType.STRING, length = 3)
@Access(AccessType.FIELD)
public abstract class Pessoa implements Serializable{

    @Id
    @Column(name = "pessoa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPessoa;
    @Column(name = "pessoa_txt_nome")
    private String nome;

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
