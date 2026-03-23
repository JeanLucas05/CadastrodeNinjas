package dev.java10x.CadastrodeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    // DTO -> ENTITY
    public NinjaModel map(NinjaDTO ninja){
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninja.getId());
        ninjaModel.setNome(ninja.getNome());
        ninjaModel.setEmail(ninja.getEmail());
        ninjaModel.setIdade(ninja.getIdade());
        ninjaModel.setRank(ninja.getRank());
        ninjaModel.setImgurl(ninja.getImgurl());
        ninjaModel.setMissoes(ninja.getMissoes());


        return ninjaModel;
    }

    // ENTITY -> DTO
    public NinjaDTO map(NinjaModel ninjaModel){
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setRank(ninjaModel.getRank());
        ninjaDTO.setImgurl(ninjaModel.getImgurl());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());



        return ninjaDTO;
    }
}
