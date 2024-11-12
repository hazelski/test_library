package com.test.library.service;

import com.test.library.model.autor.domain.AutorDomain;
import com.test.library.model.autor.dto.AutorRequestDTO;
import com.test.library.model.autor.dto.AutorResponseDTO;
import com.test.library.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired //Utilizando @Autowired explicitamente pois o Mockito não está sendo capaz de  compreender o @RequiredArgsConstructor
    private AutorRepository repository;

    public AutorResponseDTO create(AutorRequestDTO request) {
        AutorDomain domain = AutorDomain.builder()
                .nome(request.getNome())
                .nacionalidade(request.getNacionalidade())
                .dataNascimento(request.getDataNascimento())
                .build();
        return save(domain);
    }

    public List<AutorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(AutorResponseDTO::domainToDTO)
                .collect(Collectors.toList());
    }

    public AutorResponseDTO findById(UUID id) {
        return repository.findById(id).map(AutorResponseDTO::domainToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
    }

    public AutorResponseDTO update(UUID id, AutorRequestDTO request) {
        AutorDomain domain = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        domain.setNome(request.getNome());
        domain.setNacionalidade(request.getNacionalidade());
        domain.setDataNascimento(request.getDataNascimento());

        return save(domain);
    }

    public void delete(UUID id) {
        AutorDomain domain = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
        repository.delete(domain);
    }

    private AutorResponseDTO save(AutorDomain domain) {
        System.out.println(repository.hashCode());
        return Optional.of(repository.save(domain))
                .map(AutorResponseDTO::domainToDTO).get();
    }
}
