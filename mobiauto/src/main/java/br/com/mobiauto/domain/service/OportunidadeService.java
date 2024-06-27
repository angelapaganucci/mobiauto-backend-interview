package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.dto.OportunidadeDTO;
import br.com.mobiauto.domain.dto.UsuarioDTO;
import br.com.mobiauto.domain.model.Cargo;
import br.com.mobiauto.domain.model.Oportunidade;
import br.com.mobiauto.domain.model.StatusOportunidade;
import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.repository.OportunidadeRepository;
import com.github.dockerjava.api.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OportunidadeService {

    private final OportunidadeRepository oportunidadeRepository;

    @Transactional
    public List<OportunidadeDTO> buscarTodasAsOportunidades(UsuarioDTO usuarioAtual) {
        if (usuarioAtual.getCargo() != Cargo.PROPRIETARIO && usuarioAtual.getCargo() != Cargo.GERENTE) {
            List<OportunidadeDTO> oportunidadeDTOs = oportunidadeRepository.findAllByResponsavel(usuarioAtual.getId());
            return oportunidadeDTOs.stream()
                    .map(OportunidadeDTO::toOportunidade)
                    .map(OportunidadeDTO::fromOportunidade)
                    .collect(Collectors.toList());
        }

        return oportunidadeRepository.findAll().stream()
                .map(OportunidadeDTO::fromOportunidade)
                .collect(Collectors.toList());
    }

    @Transactional
    public OportunidadeDTO criarOportunidade(OportunidadeDTO oportunidadeDTO, UsuarioDTO usuarioAtual) {
        if (oportunidadeDTO.getResponsavel() != null && oportunidadeDTO.getResponsavel().getId() != usuarioAtual.getId()
            && usuarioAtual.getCargo() != Cargo.PROPRIETARIO && usuarioAtual.getCargo() != Cargo.GERENTE) {
            throw new UnauthorizedException("Apenas o usuário associado à oportunidade, gerentes e proprietários podem editar a oportunidade");
        }
        Oportunidade oportunidade = oportunidadeDTO.toOportunidade();
        oportunidade.setStatus(StatusOportunidade.NOVO);
        oportunidade = oportunidadeRepository.save(oportunidade);

        return OportunidadeDTO.fromOportunidade(oportunidade);

    }

    public OportunidadeDTO atribuirOportunidade(Long oportunidadeId, Usuario usuario) {
        Oportunidade oportunidade = oportunidadeRepository.findById(oportunidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("Oportunidade não encontrada"));

        oportunidade.setResponsavel(usuario);
        oportunidade.setDataAtribuicao(LocalDateTime.now());

        oportunidade = oportunidadeRepository.save(oportunidade);
        return OportunidadeDTO.fromOportunidade(oportunidade);
    }

    public OportunidadeDTO transferirOportunidade(Long oportunidadeId, Usuario novoResponsavel, Usuario usuarioAtual) {
        if (usuarioAtual.getCargo() != Cargo.PROPRIETARIO && usuarioAtual.getCargo() != Cargo.GERENTE) {
            throw new UnauthorizedException("Apenas proprietários e gerentes podem transferir oportunidades");
        }

        Oportunidade oportunidade = oportunidadeRepository.findById(oportunidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("Oportunidade não encontrada"));

        oportunidade.setResponsavel(novoResponsavel);

        oportunidade = oportunidadeRepository.save(oportunidade);
        return OportunidadeDTO.fromOportunidade(oportunidade);
    }

}
