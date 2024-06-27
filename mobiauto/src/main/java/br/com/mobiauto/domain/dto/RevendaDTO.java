package br.com.mobiauto.domain.dto;

import br.com.mobiauto.domain.model.Revenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevendaDTO {
    private Long id;
    private String codigoIdentificador;
    private String nomeSocial;
    private String cnpj;

    public static RevendaDTO fromRevenda(Revenda revenda) {
        RevendaDTO revendaDTO = new RevendaDTO();
        revendaDTO.id = revenda.getId();
        revendaDTO.codigoIdentificador = revenda.getCodigoIdentificador();
        revendaDTO.nomeSocial = revenda.getNomeSocial();
        revendaDTO.cnpj = revenda.getCnpj();
        return revendaDTO;
    }

    public Revenda toRevenda() {
        Revenda revenda = new Revenda();
        revenda.setCodigoIdentificador(this.getCodigoIdentificador());
        revenda.setNomeSocial(this.getNomeSocial());
        revenda.setCnpj(this.getCnpj());
        return revenda;
    }
}