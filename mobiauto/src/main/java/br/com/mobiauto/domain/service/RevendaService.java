package br.com.mobiauto.domain.service;

import br.com.mobiauto.domain.dto.RevendaDTO;
import br.com.mobiauto.domain.model.Revenda;
import br.com.mobiauto.domain.repository.RevendaRepository;
import br.com.mobiauto.domain.util.CnpjUtils;
import br.com.mobiauto.domain.validator.RevendaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RevendaService {

    private final RevendaRepository revendaRepository;
    private final RevendaValidator revendaValidator;


    @Transactional
    public RevendaDTO criarRevenda(RevendaDTO revendaDTO) {
        String cnpj = CnpjUtils.validaEFormataCNPJ(revendaDTO.getCnpj());
        String cnpjSemPontuacao = CnpjUtils.removerPontuacaoCNPJ(cnpj);
        revendaDTO.setCnpj(cnpjSemPontuacao);
        revendaValidator.validate(revendaDTO);
        Revenda revenda = revendaDTO.toRevenda();
        revenda = revendaRepository.save(revenda);
        return RevendaDTO.fromRevenda(revenda);
    }


    public RevendaDTO atualizarRevenda(Long id, RevendaDTO revendaDTO) {
        Revenda revenda = revendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Revenda não encontrada"));
        String cnpj = CnpjUtils.validaEFormataCNPJ(revendaDTO.getCnpj());
        String cnpjSemPontuacao = CnpjUtils.removerPontuacaoCNPJ(cnpj);
        revendaValidator.validate(revendaDTO);
        revenda.setCodigoIdentificador(revendaDTO.getCodigoIdentificador());
        revenda.setNomeSocial(revendaDTO.getNomeSocial());
        revenda.setCnpj(cnpjSemPontuacao);
        revenda = revendaRepository.save(revenda);
        return RevendaDTO.fromRevenda(revenda);
    }

    public List<RevendaDTO> buscarTodasRevendas() {
        return revendaRepository.findAll().stream()
                .map(RevendaDTO::fromRevenda)
                .collect(Collectors.toList());
    }
}