package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.dto.OportunidadeDTO;
import br.com.mobiauto.domain.model.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

    @Query("""
            SELECT OportunidadeDTO(
            o.id, 
            o.codigoIdentificador, 
            o.status, 
            o.motivoConclusao,
            o.cliente.id, 
            o.veiculo.id,
            o.revenda.id,
            o.responsavel.id,
            o.dataAtribuicao,
            o.dataConclusao
            ) 
            FROM Oportunidade o
            WHERE o.responsavel.id = :idUsuario
            """)
    List<OportunidadeDTO> findAllByResponsavel(Long idUsuario);

}
