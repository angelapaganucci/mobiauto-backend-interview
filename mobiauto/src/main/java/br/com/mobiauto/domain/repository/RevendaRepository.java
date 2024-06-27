package br.com.mobiauto.domain.repository;

import br.com.mobiauto.domain.model.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevendaRepository extends JpaRepository<Revenda, Long> {
    boolean existsByCodigoIdentificador(String codigoIdentificador);
}