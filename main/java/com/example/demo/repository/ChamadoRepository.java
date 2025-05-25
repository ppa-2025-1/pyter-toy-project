package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.model.entity.Chamado;

public interface ChamadoRepository extends BaseRepository<Chamado, Integer> {
    Optional<Chamado> findById(Integer id);
}
