package com.example.demo.model.business;

import com.example.demo.dto.NewChamado;
import com.example.demo.model.entity.Chamado;
import com.example.demo.repository.ChamadoRepository;

import enums.Situacao;

@Business
public class ChamadoBusiness {
    private ChamadoRepository chamadoRepository;

    public ChamadoBusiness(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public void criarChamado(NewChamado newChamado) {
        // BUSINESS RULES
        // DOMAIN RULES
        
        Chamado chamado = new Chamado();
        chamado.setAcao(newChamado.acao());
        chamado.setObjeto(newChamado.objeto());
        chamado.setDetalhamento(newChamado.detalhamento());
        
        chamado.setSituacao(Situacao.valueOf(Situacao.PENDENTE.name()));
        
        chamadoRepository.save(chamado);
    }
}
