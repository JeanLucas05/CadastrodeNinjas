package dev.java10x.CadastrodeNinjas.Missoes;


import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {
    private Long id;

    @NotBlank
    private String nomedamissao;

    @NotBlank
    private String dificuldade;

    private List<NinjaModel> ninjas;
}
