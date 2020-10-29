package com.sylleryum.meajudaaajudar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sylleryum.meajudaaajudar.assembler.HATEOASBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cidade")
public class Cidade extends HATEOASBuilder{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
//    @GenericGenerator(name = "seq", strategy="increment")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id")
    @JsonBackReference
    private Estado estado;
    public String getUf() {
        return estado.getUf();
    }

    @OneToMany(mappedBy = "cidadeEntity",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contato> contato;

    //@JsonManagedReference
    @OneToMany(mappedBy = "cidadeEntity",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Instituicao> instituicoes;

    public Cidade() {
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Contato> getContato() {
        return contato;
    }

    public void setContato(List<Contato> contato) {
        this.contato = contato;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

//    public HATEOASLinks get_Links(){
////        Map<String,String> m = new HashMap<>();
////        m.put("href","httttttp://localhost:8080/api/v1/cidades/"+this.getId());
////        return new HATEOASLinks(m);
//        //return new HATEOASLinks("http://localhost:8080/api/v1/cidades/"+this.getId());
//        return null;
//    }

//    @Override
//    public String toString() {
//        return "Cidade{" +
//                "id=" + id +
//                ", nome='" + nome + '\'' +
//                ", uf=" + estado.getUf() +
////                ", contato=" + contato +
////                ", instituicoes=" + instituicoes +
//                '}';
//    }
}
