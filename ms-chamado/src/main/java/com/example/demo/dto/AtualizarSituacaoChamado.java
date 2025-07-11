package main.java.com.example.demo.dto;

import enums.Situacao;


public class AtualizarSituacaoChamado{
    private Situacao novaSituacao;

    public Situacao getNovaSituacao() {
        return novaSituacao;
    }

    public void setNovaSituacao(Situacao novaSituacao) {
        this.novaSituacao = novaSituacao;
    }
}