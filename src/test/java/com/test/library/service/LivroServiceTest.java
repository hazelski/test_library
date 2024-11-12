package com.test.library.service;

import com.test.library.model.autor.domain.AutorDomain;
import com.test.library.model.livro.domain.LivroDomain;
import com.test.library.model.livro.dto.LivroRequestDTO;
import com.test.library.model.livro.dto.LivroResponseDTO;
import com.test.library.repository.AutorRepository;
import com.test.library.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private LivroService livroService;

    private UUID livroId;
    private UUID autorId;
    private LivroRequestDTO livroRequestDTO;
    private LivroDomain livroDomain;
    private AutorDomain autorDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        livroId = UUID.randomUUID();
        autorId = UUID.randomUUID();

        autorDomain = AutorDomain.builder().id(autorId).build();

        livroDomain = LivroDomain.builder()
                .id(livroId)
                .titulo("Título do Livro")
                .isbn("123456789")
                .dataPublicacao(LocalDate.now().minusYears(2L))
                .autor(autorDomain)
                .build();

        livroRequestDTO = new LivroRequestDTO();
        livroRequestDTO.setTitulo("Título do Livro");
        livroRequestDTO.setIsbn("123456789");
        livroRequestDTO.setDataPublicacao(LocalDate.now().minusYears(2L));
        livroRequestDTO.setAutorId(autorId);
    }

    @Test
    void testCreateLivroSuccess() {
        when(autorRepository.findById(autorId)).thenReturn(Optional.of(autorDomain));
        when(livroRepository.save(any(LivroDomain.class))).thenReturn(livroDomain);

        LivroResponseDTO response = livroService.create(livroRequestDTO);

        assertNotNull(response);
        assertEquals(livroDomain.getTitulo(), response.getTitulo());
        verify(livroRepository, times(1)).save(any(LivroDomain.class));
    }

    @Test
    void testCreateLivroAutorNotFound() {
        when(autorRepository.findById(autorId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> livroService.create(livroRequestDTO));
        verify(livroRepository, never()).save(any(LivroDomain.class));
    }

    @Test
    void testFindAllLivros() {
        when(livroRepository.findAll()).thenReturn(List.of(livroDomain));

        List<LivroResponseDTO> livros = livroService.findAll();

        assertNotNull(livros);
        assertEquals(1, livros.size());
        assertEquals(livroDomain.getTitulo(), livros.get(0).getTitulo());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void testFindLivroByIdSuccess() {
        when(livroRepository.findById(livroId)).thenReturn(Optional.of(livroDomain));

        LivroResponseDTO response = livroService.findById(livroId);

        assertNotNull(response);
        assertEquals(livroDomain.getTitulo(), response.getTitulo());
        verify(livroRepository, times(1)).findById(livroId);
    }

    @Test
    void testFindLivroByIdNotFound() {
        when(livroRepository.findById(livroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> livroService.findById(livroId));
        verify(livroRepository, times(1)).findById(livroId);
    }

    @Test
    void testUpdateLivroSuccess() {
        when(livroRepository.findById(livroId)).thenReturn(Optional.of(livroDomain));
        when(autorRepository.findById(autorId)).thenReturn(Optional.of(autorDomain));

        LivroRequestDTO updatedRequestDTO = new LivroRequestDTO();
        updatedRequestDTO.setTitulo("Novo Título");
        updatedRequestDTO.setIsbn("987654321");
        LocalDate dataPublicacao = LocalDate.now().minusYears(1);
        updatedRequestDTO.setDataPublicacao(dataPublicacao);
        updatedRequestDTO.setAutorId(autorId);

        livroService.update(livroId, updatedRequestDTO);

        assertEquals("Novo Título", livroDomain.getTitulo());
        assertEquals("987654321", livroDomain.getIsbn());
        assertEquals(dataPublicacao, livroDomain.getDataPublicacao());
        assertEquals(autorDomain, livroDomain.getAutor());

        verify(livroRepository, times(1)).save(livroDomain);
    }

    @Test
    void testUpdateLivroNotFound() {
        when(livroRepository.findById(livroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> livroService.update(livroId, livroRequestDTO));
        verify(livroRepository, never()).save(any(LivroDomain.class));
    }

    @Test
    void testDeleteLivroSuccess() {
        when(livroRepository.findById(livroId)).thenReturn(Optional.of(livroDomain));

        livroService.delete(livroId);

        verify(livroRepository, times(1)).delete(livroDomain);
    }

    @Test
    void testDeleteLivroNotFound() {
        when(livroRepository.findById(livroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> livroService.delete(livroId));
        verify(livroRepository, never()).delete(any(LivroDomain.class));
    }

    @Test
    void testFindByAutorId() {
        when(livroRepository.findByAutorId(autorId)).thenReturn(List.of(livroDomain));

        List<LivroResponseDTO> livros = livroService.findByAutorId(autorId);

        assertNotNull(livros);
        assertEquals(1, livros.size());
        assertEquals(livroDomain.getTitulo(), livros.get(0).getTitulo());
        verify(livroRepository, times(1)).findByAutorId(autorId);
    }
}
