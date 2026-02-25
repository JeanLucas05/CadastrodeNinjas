package dev.java10x.CadastrodeNinjas;

import jakarta.persistence.*;

// Entity transforma uma classe em uma entidade do Banco de dados
// JPA = JAVA PERSISTENCE API
@Entity
@Table(name = "tb_cadastro_de_ninjas")//Nome da tabela
public class NinjaModel {

    @Id // Define que o atributo abaixo dele vai ser usado como id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String email;
    int idade;


    public NinjaModel() {
    }

    public NinjaModel(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
