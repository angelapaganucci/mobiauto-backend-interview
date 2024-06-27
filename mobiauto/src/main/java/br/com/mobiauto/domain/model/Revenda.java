package br.com.mobiauto.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Revenda")
@Table(name = "revenda")
@NoArgsConstructor
@AllArgsConstructor
public class Revenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_identificador", unique = true, nullable = false)
    private String codigoIdentificador;

    @Column(name = "nome_social")
    private String nomeSocial;

    @Column(name = "cnpj", length = 14, unique = true, nullable = false)
    private String cnpj;

    @OneToMany(mappedBy = "revenda")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "revenda")
    private List<Oportunidade> oportunidades;

}
