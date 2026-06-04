package dev.java10x.CadastrodeNinjas.Ninjas;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import dev.java10x.CadastrodeNinjas.Missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NinjaDTO {

    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @Min(1)
    private Integer idade;

    @NotBlank
    private String rank;

    private String imgurl;

    private MissoesModel missoes;

}
