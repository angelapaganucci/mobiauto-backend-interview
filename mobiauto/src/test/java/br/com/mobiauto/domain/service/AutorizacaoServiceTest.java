package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorizacaoServiceTest {

    @InjectMocks
    private AutorizacaoService autorizacaoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveCarregarUsuarioPeloUsername() {
        Usuario usuario = new Usuario("1","nome", "email", null, null, "", null);
        when(usuarioRepository.findByEmail("")).thenReturn(usuario);

        UserDetails result = autorizacaoService.loadUserByUsername("");

        assertEquals("email", result.getUsername());
    }

}