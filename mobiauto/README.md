# mobiauto-backend-interview
Teste técnico Mobiauto
# Informações Gerais
Criei um front-end em angular para consumir a api do projeto, o front-end encontra-se no repositório
https://github.com/angelapaganucci/login-page. 
Para rodar o front-end é necessário ter o node instalado na máquina, após clonar o repositório, 
acessar a pasta do projeto e rodar o comando npm install para instalar as dependências do 
projeto e após isso rodar o comando ng serve para subir o servidor localmente.
Com esse front, é possivel criar um usuario e depois de criado logar com esse usuario.
![img_1.png](img_1.png)
![img.png](img.png)
![img_2.png](img_2.png)

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
