package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.NewChamado;
import com.example.demo.dto.NewUser;
import com.example.demo.model.business.ChamadoBusiness;
import com.example.demo.model.entity.Chamado;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ChamadoRepository;

import enums.Situacao;
import jakarta.validation.Valid;
import main.java.com.example.demo.dto.AtualizarSituacaoChamado;

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController extends AbstractController {
    private final ChamadoBusiness chamadoBusiness;
    private final ChamadoRepository chamadoRepository;

    public ChamadoController(ChamadoBusiness chamadoBusiness, ChamadoRepository chamadoRepository) {
        this.chamadoBusiness = chamadoBusiness;
        this.chamadoRepository = chamadoRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewChamado(
        @Valid
        @RequestBody
        NewChamado newChamado) {

        chamadoBusiness.criarChamado(newChamado);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Chamado>> getChamados() {
        return ResponseEntity.ok(chamadoRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chamado> getChamadosById(@PathVariable Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        if (chamado.isPresent()) {
            return ResponseEntity.ok(chamado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateChamado(
    @PathVariable Integer id,
    @Valid
    @RequestBody
    AtualizarSituacaoChamado atualizarSituacaoChamado) {
    
        Optional<Chamado> chamadoAtual = chamadoRepository.findById(id);
        Chamado chamado = chamadoAtual.get();
    
        Situacao atual = chamado.getSituacao();
        Situacao nova = atualizarSituacaoChamado.getNovaSituacao();

        if(!podeAlterarSituacao(atual, nova)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao e possivel alterar a situacao do chamado");
        }

        chamado.setSituacao(nova);
        chamadoRepository.save(chamado);

        return ResponseEntity.ok("Situacao alterada com sucesso");
    }

    private boolean podeAlterarSituacao(Situacao atual, Situacao nova) {
        switch (atual) {
            case PENDENTE:
                return nova == Situacao.EM_ANDAMENTO || nova == Situacao.CANCELADO;
            case EM_ANDAMENTO:
                return nova == Situacao.CANCELADO || nova == Situacao.FINALIZADO;
            default:
                return false;
        }
}

}
