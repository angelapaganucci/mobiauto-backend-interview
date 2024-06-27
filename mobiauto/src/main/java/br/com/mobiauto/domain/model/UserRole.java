package br.com.mobiauto.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;
}
