package br.com.mobiauto.domain.validator;

import br.com.mobiauto.domain.dto.RevendaDTO;
import br.com.mobiauto.domain.repository.RevendaRepository;
import br.com.mobiauto.domain.util.CnpjUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RevendaValidator {

    private final RevendaRepository revendaRepository;

    @Autowired
    public RevendaValidator(RevendaRepository revendaRepository) {
        this.revendaRepository = revendaRepository;
    }

    public void validate(RevendaDTO dto) {
        validateCodigoIdentificador(dto.getCodigoIdentificador());
        validateCnpj(dto.getCnpj());
        validateNomeSocial(dto.getNomeSocial());
    }

    private void validateCodigoIdentificador(String codigoIdentificador) {
        if (StringUtils.isEmpty(codigoIdentificador)) {
            throw new IllegalArgumentException("Código identificador não pode ser vazio");
        }
        if (revendaRepository.existsByCodigoIdentificador(codigoIdentificador)) {
            throw new IllegalArgumentException("Código identificador já cadastrado");
        }
    }

    private void validateCnpj(String cnpj) {
        if (StringUtils.isEmpty(cnpj)) {
            throw new IllegalArgumentException("CNPJ não pode ser vazio");
        }
        if (!CnpjUtils.isValidCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    private void validateNomeSocial(String nomeSocial) {
        if (StringUtils.isEmpty(nomeSocial)) {
            throw new IllegalArgumentException("Nome social não pode ser vazio");
        }
    }
}