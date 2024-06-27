package br.com.mobiauto.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Oportunidade")
@Table(name = "oportunidade")
@NoArgsConstructor
@AllArgsConstructor
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "codigo_identificador", unique = true, nullable = false)
    private String codigoIdentificador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOportunidade status;

    @Column(name = "motivo_conclusao")
    private String motivoConclusao;

    @JoinColumn(name = "cliente_id", nullable = false)
    @ManyToOne
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "revenda_id", referencedColumnName = "id")
    private Revenda revenda;

    @ManyToOne
    private Usuario responsavel;

    @Column(name = "data_atribuicao")
    private LocalDateTime dataAtribuicao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
}
