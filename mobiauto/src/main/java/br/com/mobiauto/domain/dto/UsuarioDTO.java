package br.com.mobiauto.domain.dto;

import br.com.mobiauto.domain.model.Cargo;
import br.com.mobiauto.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String codigoIdentificador;
    private String nome;
    private String email;
    private String senha;
    private Cargo cargo;

    public static UsuarioDTO fromUsuario(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.id = usuario.getId();
        usuarioDTO.codigoIdentificador = usuario.getCodigoIdentificador();
        usuarioDTO.nome = usuario.getNome();
        usuarioDTO.email = usuario.getEmail();
        usuarioDTO.senha = usuario.getSenha();
        usuarioDTO.cargo = usuario.getCargo();
        return usuarioDTO;
    }
}