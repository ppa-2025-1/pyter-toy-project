package com.example.demo.model.entity;

import enums.Situacao;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "chamados")
public class Chamado extends BaseEntity {
    
    @Column(nullable = false, length = 255)
    private String acao;

    @Column(nullable = false, length = 255)
    private String objeto;

    @Column(nullable = false, length = 255)
    private String detalhamento;
    
    @Column(nullable = false, length = 255)
    private Situacao situacao;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

}
