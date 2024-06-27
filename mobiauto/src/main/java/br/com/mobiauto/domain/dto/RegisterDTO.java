package br.com.mobiauto.domain.dto;

import br.com.mobiauto.domain.model.Cargo;
import br.com.mobiauto.domain.model.Revenda;
import br.com.mobiauto.domain.model.UserRole;

public record RegisterDTO(String codigoIdentificador, String nome, String email, String senha, Cargo cargo, Revenda revenda, UserRole role) {
}
