package com.test.library.controller;

import com.test.library.model.autor.dto.AutorRequestDTO;
import com.test.library.model.autor.dto.AutorResponseDTO;
import com.test.library.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/autor")

public class AutorController {

    @Autowired
    private AutorService service;

    @Operation(summary = "Autor", description = "Cadastrar autor", tags = {"Autor"})
    @PostMapping()
    public ResponseEntity<AutorResponseDTO> criar(@RequestBody @Valid AutorRequestDTO request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Autor", description = "Listar todos os autores", tags = {"Autor"})
    @GetMapping()
    public ResponseEntity<List<AutorResponseDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Autor", description = "Buscar autor pelo ID", tags = {"Autor"})
    @GetMapping("/{ID}")
    public ResponseEntity<AutorResponseDTO> findById(@PathVariable("ID") UUID id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Autor", description = "Editar autor", tags = {"Autor"})
    @PutMapping("/{ID}")
    public ResponseEntity<AutorResponseDTO> editar(@PathVariable("ID") UUID id,
                                                        @RequestBody AutorRequestDTO request){
        return new ResponseEntity<>(service.update(id, request), HttpStatus.CREATED);
    }

    @Operation(summary = "Autor", description = "Remover autor", tags = {"Autor"})
    @DeleteMapping("/{ID}")
    public ResponseEntity<?> delete(@PathVariable("ID") UUID id){
        service.delete(id);
        return new ResponseEntity<>("Removido com sucesso!", HttpStatus.ACCEPTED);
    }
}
