package dev.java10x.CadastrodeNinjas.Ninjas.Model;

import dev.java10x.CadastrodeNinjas.Missoes.Model.MissoesModel;
import jakarta.persistence.*;


// Entity transforma uma classe em uma entidade do Banco de dados
// JPA = JAVA PERSISTENCE API
@Entity
@Table(name = "tb_cadastro_de_ninjas")//Nome da tabela
public class NinjaModel {

    @Id // Define que o atributo abaixo dele vai ser usado como id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private int idade;
    // @ManyToOne um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id")//Foregin Key ou Chave Estrangeira
    private MissoesModel missoes;


    public NinjaModel() {
    }

    public NinjaModel(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public NinjaModel(Long id, String nome, String email, int idade, MissoesModel missoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.missoes = missoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public MissoesModel getMissoes() {
        return missoes;
    }

    public void setMissoes(MissoesModel missoes) {
        this.missoes = missoes;
    }
}

