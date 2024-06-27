package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.dto.UsuarioDTO;
import br.com.mobiauto.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public List<UsuarioDTO> buscarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::fromUsuario)
                .collect(Collectors.toList());
    }
}
