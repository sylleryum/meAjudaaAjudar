package com.sylleryum.meajudaaajudar.entity;

import com.fasterxml.jackson.annotation.*;
import com.sylleryum.meajudaaajudar.assembler.HATEOASBuilder;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "estado")
public class Estado extends HATEOASBuilder{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String uf;
//    @Transient
//    private List<Link> links;
//    public void setLinks(List<Link> links) {
//        this.links = links;
//    }
//    public List<Link> get_Linx(){
//        return this.links;
//    }

    @OneToMany(
            mappedBy = "estado",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cidade> cidades;

    public Estado() {
    }

    public Estado(String nome, String uf, List<Cidade> cidades) {
        this.nome = nome;
        this.uf = uf;
        this.cidades = cidades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }



    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                ", cidades=" + cidades +
                '}';
    }
}
