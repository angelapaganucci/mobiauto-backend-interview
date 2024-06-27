package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.dto.UsuarioDTO;
import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveBuscarTodosUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioDTO> result = usuarioService.buscarTodos();

        assertEquals(usuarios.size(), result.size());
    }
}