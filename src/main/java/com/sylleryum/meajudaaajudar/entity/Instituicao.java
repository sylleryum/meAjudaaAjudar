package com.sylleryum.meajudaaajudar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sylleryum.meajudaaajudar.assembler.HATEOASBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "instituicao")
@JsonPropertyOrder({ "id", "nome", "imagem", "descricao", "cidade", "causa", "sobre", "url", "doarLink" })
public class Instituicao extends HATEOASBuilder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    private String imagem;
    private String descricao;
    private String sobre;
    private String url;
    private String doarLink;
    private String infoDoacao;

    /*
    properties sent on get:
    this is to read only because the when POSTing a Instituicao Causa an ID should be passed instead, therefore, below var causaId
     */
    @JsonProperty(value = "cidade", access = JsonProperty.Access.READ_ONLY)
    public String getCidade() {
        return cidadeEntity.getNome();
    }

    ////////@JsonProperty(value = "contato", access = JsonProperty.Access.READ_ONLY)
    @OneToOne
    @JoinColumn(name = "contato_id", referencedColumnName = "id")
    private Contato contato;

    @ManyToOne
    @JoinColumn(name = "causa_id")
    @JsonProperty(value = "causa", access = JsonProperty.Access.READ_ONLY)
    private Causa causa;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    @JsonIgnore
    private Cidade cidadeEntity;


    /*
       properties received on POST
       to get foreign keys and map it to corresponding entities, the GET methods will send an entity, these below will receive an ID
     */
    @Column(name = "causa_id", insertable = false, updatable = false)
    @JsonProperty(value = "causa", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private Long causaId;

    @Column(name = "cidade_id", insertable = false, updatable = false)
    @JsonProperty(value = "cidade", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private Long cidadeId;

//    @Column(name = "contato_id", insertable = false, updatable = false)
//    @JsonProperty(value = "contato", access = JsonProperty.Access.WRITE_ONLY)
//    @NotNull
//    private Long contatoId;


    public Instituicao() {
    }

    public Instituicao(String nome, String imagem, String descricao, String sobre, String url, String doarLink, Contato contato, Causa causa, Cidade cidade, String infoDoacao) {
        this.nome = nome;
        this.imagem = imagem;
        this.descricao = descricao;
        this.sobre = sobre;
        this.url = url;
        this.doarLink = doarLink;
        this.contato = contato;
        this.causa = causa;
        this.cidadeEntity = cidade;
        this.infoDoacao = infoDoacao;
    }

    @Override
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDoarLink() {
        return doarLink;
    }

    public void setDoarLink(String doarLink) {
        this.doarLink = doarLink;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Causa getCausa() {
        return causa;
    }

    public void setCausa(Causa causa) {
        this.causa = causa;
    }

    public Cidade getCidadeEntity() {
        return cidadeEntity;
    }

    public void setCidadeEntity(Cidade cidade) {
        this.cidadeEntity = cidade;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidade_id) {
        this.cidadeId = cidade_id;
    }

    public Long getCausaId() {
        return causaId;
    }

    public void setCausaId(Long causa_id) {
        this.causaId = causa_id;
    }

    public String getInfoDoacao() {
        return infoDoacao;
    }

    public void setInfoDoacao(String infoDoacao) {
        this.infoDoacao = infoDoacao;
    }

    //    public Long getContatoId() {
//        return contatoId;
//    }
//
//    public void setContatoId(Long contato_id) {
//        this.contatoId = contato_id;
//    }
}
