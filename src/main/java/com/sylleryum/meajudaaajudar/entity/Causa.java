package com.sylleryum.meajudaaajudar.entity;

import com.sylleryum.meajudaaajudar.assembler.HATEOASBuilder;

import javax.persistence.*;

@Entity
@Table(name = "causa")
public class Causa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String causa;
    private String subCausa;

//    @OneToMany(mappedBy = "causa",
//            cascade = CascadeType.ALL)
//    private List<Instituicao> instituicao;

    public Causa() {
    }

    public Causa(String causa, String subCausa/*, List<Instituicao> instituicao*/) {
        this.causa = causa;
        this.subCausa = subCausa;
        //this.instituicao = instituicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getSubCausa() {
        return subCausa;
    }

    public void setSubCausa(String subCausa) {
        this.subCausa = subCausa;
    }

    /*public List<Instituicao> getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(List<Instituicao> instituicao) {
        this.instituicao = instituicao;
    }*/


}
