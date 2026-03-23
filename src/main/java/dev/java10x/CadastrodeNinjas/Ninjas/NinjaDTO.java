package dev.java10x.CadastrodeNinjas.Ninjas;

import dev.java10x.CadastrodeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String rank;
    private String imgurl;
    private MissoesModel missoes;

}
