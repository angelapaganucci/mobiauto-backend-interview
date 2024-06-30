# mobiauto-backend-interview
Teste técnico Mobiauto
## 1. Introdução:
* O sistema Mobiauto tem como objetivo fornecer uma poderosa ferramenta de gestão de Revendas
de veículos
## 2. Visão Geral:
### Para esse software foram utilizadas as seguintes tecnologias:
* Java 17
* Maven
* Springboot
* Spring security
* Lombok
* Spring jpa
* Spring jwt
* Banco de dados foi utilizado o H2 e para acessar o console desse banco as informações com url, ususario e senha encontra-se no application.yml do projeto
## Diagrama de classes
```mermaid
classDiagram
    class Cliente {
        Long id
        String nome
        String email
        String telefone
    }

    class Oportunidade {
        Long id
        String codigoIdentificador
        StatusOportunidade status
        String motivoConclusao
        LocalDateTime dataAtribuicao
        LocalDateTime dataConclusao
    }

    Oportunidade --> Cliente : cliente
    Oportunidade --> Veiculo : veiculo
    Oportunidade --> Revenda : revenda
    Oportunidade --> Usuario : responsavel

    class Revenda {
        Long id
        String codigoIdentificador
        String nomeSocial
        String cnpj
    }

    Revenda --> Usuario : usuarios
    Revenda --> Oportunidade : oportunidades

    class Usuario {
        Long id
        String codigoIdentificador
        String nome
        String email
        String senha
        Cargo cargo
        UserRole role
    }

    Usuario --> Revenda : revenda

    class Veiculo {
        Long id
        String marca
        String modelo
        String versao
        String anoModelo
    }

```

## 3. Suporte e Contato:
* Dúvidas entre em contato no e-mail angelapaganucci@hotmail.com
