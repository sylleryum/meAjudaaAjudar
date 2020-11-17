package com.sylleryum.meajudaaajudar.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    @JsonIgnore
    Instituicao instituicaoEntity;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime time;

    @Transient
    @JsonProperty(value = "instituicao", access = JsonProperty.Access.WRITE_ONLY)
    private Long instituicaoId;

    public Long getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Long instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public Donation() {
    }

    public Donation(Instituicao instituicaoEntity) {
        this.instituicaoEntity = instituicaoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instituicao getInstituicaoEntity() {
        return instituicaoEntity;
    }

    public void setInstituicaoEntity(Instituicao instituicao) {
        this.instituicaoEntity = instituicao;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime createDateTime) {
        this.time = createDateTime;
    }
}
