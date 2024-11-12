package com.test.library.service;

import com.test.library.model.autor.domain.AutorDomain;
import com.test.library.model.autor.dto.AutorRequestDTO;
import com.test.library.model.autor.dto.AutorResponseDTO;
import com.test.library.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

    @Mock
    private AutorRepository repository;

    @InjectMocks
    private AutorService service;

    private AutorDomain autor;
    private AutorRequestDTO autorRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        autor = AutorDomain.builder()
                .id(UUID.randomUUID())
                .nome("João Silva")
                .nacionalidade("Brasileira")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        autorRequest = AutorRequestDTO.builder()
                .nome("João Silva")
                .nacionalidade("Brasileira")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();
    }

    @Test
    void create_shouldSaveAndReturnAutorResponseDTO() {
        System.out.println(repository.hashCode());
        when(repository.save(any(AutorDomain.class))).thenReturn(autor);

        AutorResponseDTO response = service.create(autorRequest);

        assertNotNull(response);
        assertEquals("João Silva", response.getNome());
        verify(repository, times(1)).save(any(AutorDomain.class));
    }

    @Test
    void findAll_shouldReturnListOfAutorResponseDTO() {
        when(repository.findAll()).thenReturn(Collections.singletonList(autor));

        List<AutorResponseDTO> autores = service.findAll();

        assertFalse(autores.isEmpty());
        assertEquals(1, autores.size());
        assertEquals("João Silva", autores.get(0).getNome());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnAutorResponseDTO() {
        UUID id = autor.getId();
        when(repository.findById(id)).thenReturn(Optional.of(autor));

        AutorResponseDTO response = service.findById(id);

        assertNotNull(response);
        assertEquals("João Silva", response.getNome());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.findById(id));

        assertEquals("Autor não encontrado", exception.getMessage());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void update_shouldUpdateAndReturnAutorResponseDTO() {
        UUID id = autor.getId();
        when(repository.findById(id)).thenReturn(Optional.of(autor));
        when(repository.save(any(AutorDomain.class))).thenReturn(autor);

        AutorResponseDTO response = service.update(id, autorRequest);

        assertNotNull(response);
        assertEquals("João Silva", response.getNome());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(autor);
    }

    @Test
    void delete_shouldDeleteAutorWhenFound() {
        UUID id = autor.getId();
        when(repository.findById(id)).thenReturn(Optional.of(autor));

        service.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(autor);
    }

    @Test
    void delete_shouldThrowExceptionWhenAutorNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.delete(id));

        assertEquals("Autor não encontrado", exception.getMessage());
        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any());
    }
}
