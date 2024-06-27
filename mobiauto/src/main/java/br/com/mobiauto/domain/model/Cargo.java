package br.com.mobiauto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Cargo {
    PROPRIETARIO("Proprietário"),
    GERENTE("Gerente"),
    ASSISTENTE("Assistente");

    private String cargo;

}
