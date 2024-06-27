package br.com.mobiauto.domain.dto;

import br.com.mobiauto.domain.model.Cliente;
import br.com.mobiauto.domain.model.Oportunidade;
import br.com.mobiauto.domain.model.Revenda;
import br.com.mobiauto.domain.model.StatusOportunidade;
import br.com.mobiauto.domain.model.Usuario;
import br.com.mobiauto.domain.model.Veiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OportunidadeDTO {
    private String codigoIdentificador;
    private String status;
    private String motivoConclusao;
    private Cliente cliente;
    private Veiculo veiculo;
    private Revenda revenda;
    private Usuario responsavel;
    private LocalDateTime dataAtribuicao;
    private LocalDateTime dataConclusao;


    public static OportunidadeDTO fromOportunidade(Oportunidade oportunidade) {
        OportunidadeDTO dto = new OportunidadeDTO();
        dto.setCodigoIdentificador(oportunidade.getCodigoIdentificador());
        dto.setStatus(oportunidade.getStatus().toString());
        dto.setMotivoConclusao(oportunidade.getMotivoConclusao());
        dto.setCliente(oportunidade.getCliente());
        dto.setVeiculo(oportunidade.getVeiculo());
        dto.setRevenda(oportunidade.getRevenda());
        dto.setResponsavel(oportunidade.getResponsavel());
        dto.setDataAtribuicao(oportunidade.getDataAtribuicao());
        dto.setDataConclusao(oportunidade.getDataConclusao());
        return dto;
    }

    public Oportunidade toOportunidade() {
        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setCodigoIdentificador(this.getCodigoIdentificador());
        oportunidade.setStatus(StatusOportunidade.valueOf(this.getStatus()));
        oportunidade.setMotivoConclusao(this.getMotivoConclusao());
        oportunidade.setCliente(this.getCliente());
        oportunidade.setVeiculo(this.getVeiculo());
        oportunidade.setRevenda(this.getRevenda());
        oportunidade.setResponsavel(this.getResponsavel());
        oportunidade.setDataAtribuicao(this.getDataAtribuicao());
        oportunidade.setDataConclusao(this.getDataConclusao());
        return oportunidade;
    }
}
