package dev.java10x.CadastrodeNinjas.Ninjas;

import dev.java10x.CadastrodeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.*;


// Entity transforma uma classe em uma entidade do Banco de dados
// JPA = JAVA PERSISTENCE API
@Entity
@Table(name = "tb_cadastro_de_ninjas")//Nome da tabela
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {

    @Id // Define que o atributo abaixo dele vai ser usado como id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private int idade;
    // @ManyToOne um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id")//Foregin Key ou Chave Estrangeira
    private MissoesModel missoes;


}

