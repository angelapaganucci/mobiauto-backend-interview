package br.com.mobiauto.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "codigo_identificador", unique = true, nullable = false)
    private String codigoIdentificador;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo")
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    private Revenda revenda;

    public Usuario(String codigoIdentificador, String nome, String email, Cargo cargo, Revenda revenda, String senha, UserRole role) {
        this.codigoIdentificador = codigoIdentificador;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.revenda = revenda;
        this.senha = senha;
        this.role = role;
    }

}
