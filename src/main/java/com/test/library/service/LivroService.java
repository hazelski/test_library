package com.test.library.service;

import com.test.library.model.autor.domain.AutorDomain;
import com.test.library.model.livro.domain.LivroDomain;
import com.test.library.model.livro.dto.LivroRequestDTO;
import com.test.library.model.livro.dto.LivroResponseDTO;
import com.test.library.repository.AutorRepository;
import com.test.library.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private AutorRepository autorRepository;
    public LivroResponseDTO create(LivroRequestDTO request) {

        AutorDomain autor = autorRepository.findById(request.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        LivroDomain domain = LivroDomain.builder()
                .titulo(request.getTitulo())
                .isbn(request.getIsbn())
                .dataPublicacao(request.getDataPublicacao())
                .autor(autor)
                .build();

        return Optional.of(repository.save(domain))
                .map(LivroResponseDTO::domainToDTO).get();
    }

    public List<LivroResponseDTO> findAll() {
        return repository.findAll().stream().map(LivroResponseDTO::domainToDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO findById(UUID id) {
        return repository.findById(id).map(LivroResponseDTO::domainToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
    }

    public void update(UUID id, LivroRequestDTO request) {
        LivroDomain domain = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        AutorDomain autor = autorRepository.findById(request.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        domain.setTitulo(request.getTitulo());
        domain.setIsbn(request.getIsbn());
        domain.setDataPublicacao(request.getDataPublicacao());
        domain.setAutor(autor);

        repository.save(domain);
    }

    public void delete(UUID id) {
        LivroDomain domain = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        repository.delete(domain);
    }

    public List<LivroResponseDTO> findByAutorId(UUID autorId) {
        return repository.findByAutorId(autorId).stream().map(LivroResponseDTO::domainToDTO)
                .collect(Collectors.toList());
    }
}
