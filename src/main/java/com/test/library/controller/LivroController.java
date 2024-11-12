package com.test.library.controller;

import com.test.library.model.livro.dto.LivroRequestDTO;
import com.test.library.model.livro.dto.LivroResponseDTO;
import com.test.library.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @Operation(summary = "Livro", description = "Cadastrar livro", tags = {"Livro"})
    @PostMapping()
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody @Valid LivroRequestDTO request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Livro", description = "Listar todos os livros", tags = {"Livro"})
    @GetMapping()
    public ResponseEntity<List<LivroResponseDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Livro", description = "Buscar livro pelo ID", tags = {"Livro"})
    @GetMapping("/{ID}")
    public ResponseEntity<LivroResponseDTO> findById(@PathVariable("ID") UUID id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Livro", description = "Editar o livro", tags = {"Livro"})
    @PutMapping("/{ID}")
    public ResponseEntity<LivroResponseDTO> editar(@PathVariable("ID") UUID id,
                                                   @RequestBody LivroRequestDTO request){
        service.update(id, request);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Livro", description = "Remover livro", tags = {"Livro"})
    @DeleteMapping("/{ID}")
    public ResponseEntity<?> delete(@PathVariable("ID") UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Livro", description = "Buscar livro pelo ID do autor", tags = {"Livro"})
    @GetMapping("/byautor/{ID}")
    public ResponseEntity<List<LivroResponseDTO>> findByAutorId(@PathVariable("ID") UUID autorId){
        return new ResponseEntity<>(service.findByAutorId(autorId), HttpStatus.OK);
    }

}
