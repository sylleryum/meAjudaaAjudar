package com.sylleryum.meajudaaajudar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sylleryum.meajudaaajudar.assembler.HATEOASBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contato")
public class Contato extends HATEOASBuilder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id")
    @JsonProperty(value = "cidade", access = JsonProperty.Access.READ_ONLY)
    private Cidade cidadeEntity;

    private String logradouro;

    private Long numero;

    private String telefone;

    @OneToOne(mappedBy = "contato")
    @JsonIgnore
    private Instituicao instituicao;

    @Column(name = "cidade_id", insertable = false, updatable = false)
    @JsonProperty(value = "cidade", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private Long cidadeId;

    public Contato() {
    }

    public Contato(String logradouro, Long numero, String telefone) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.telefone = telefone;
    }

    public Contato(Cidade cidadeEntity, String logradouro, Long numero, String telefone, Instituicao instituicao) {
        this.cidadeEntity = cidadeEntity;
        this.logradouro = logradouro;
        this.numero = numero;
        this.telefone = telefone;
        this.instituicao = instituicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cidade getCidadeEntity() {
        return cidadeEntity;
    }

    public void setCidadeEntity(Cidade cidade) {
        this.cidadeEntity = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidade_id) {
        this.cidadeId = cidade_id;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", cidade=" + cidadeEntity +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", telefone='" + telefone + '\'' +
                ", instituicao=" + instituicao +
                '}';
    }
}