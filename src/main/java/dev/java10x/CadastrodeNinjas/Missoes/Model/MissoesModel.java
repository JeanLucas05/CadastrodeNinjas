package dev.java10x.CadastrodeNinjas.Missoes.Model;

import dev.java10x.CadastrodeNinjas.Ninjas.Model.NinjaModel;
import jakarta.persistence.*;

import java.nio.MappedByteBuffer;
import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomedamissao;
    private String dificuldade;
    // @OneToMany Uma missao pode ter muitos ninjas
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> Ninjas;

    public MissoesModel() {
    }

    public MissoesModel(Long id, String nomedamissao, String dificuldade, List<NinjaModel> ninjas) {
        this.id = id;
        this.nomedamissao = nomedamissao;
        this.dificuldade = dificuldade;
        Ninjas = ninjas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomedamissao() {
        return nomedamissao;
    }

    public void setNomedamissao(String nomedamissao) {
        this.nomedamissao = nomedamissao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public List<NinjaModel> getNinjas() {
        return Ninjas;
    }

    public void setNinjas(List<NinjaModel> ninjas) {
        Ninjas = ninjas;
    }
}
