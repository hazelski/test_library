package com.test.library.service;

import com.test.library.model.autor.domain.AutorDomain;
import com.test.library.model.autor.dto.AutorRequestDTO;
import com.test.library.model.autor.dto.AutorResponseDTO;
import com.test.library.model.autor.map.AutorMapper;
import com.test.library.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorResponseDTO create(AutorRequestDTO request) {
        AutorDomain domain = AutorMapper.INSTANCE.requestToDomain(request);
        return AutorMapper.INSTANCE.domainToResponse(repository.save(domain));
    }

    public List<AutorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(a -> AutorMapper.INSTANCE.domainToResponse(a))
                .collect(Collectors.toList());
    }

    public AutorResponseDTO findById(UUID id) {
        return repository.findById(id).map(a -> AutorMapper.INSTANCE.domainToResponse(a))
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
    }

    public AutorResponseDTO update(UUID id, AutorRequestDTO request) {
        AutorDomain domain = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        domain.setNome(request.getNome());
        domain.setNacionalidade(request.getNacionalidade());
        domain.setDataNascimento(request.getDataNascimento());

        return AutorMapper.INSTANCE.domainToResponse(repository.save(domain));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
