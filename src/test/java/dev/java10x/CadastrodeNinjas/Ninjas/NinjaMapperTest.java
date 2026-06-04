package dev.java10x.CadastrodeNinjas.Ninjas;

import dev.java10x.CadastrodeNinjas.Missoes.MissoesModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NinjaMapperTest {
    private final NinjaMapper ninjaMapper = new NinjaMapper();
    @Test
    void deveConverterDtoParaModel() {
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(1L);
        missoesModel.setNomedamissao("Resgatar o Hokage");

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(1L);
        ninjaDTO.setNome("Naruto");
        ninjaDTO.setEmail("naruto@email.com");
        ninjaDTO.setIdade(17);
        ninjaDTO.setRank("Genin");
        ninjaDTO.setImgurl("imagem.jpg");
        ninjaDTO.setMissoes(missoesModel);

        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);

        assertNotNull(ninjaModel);

        assertEquals(ninjaDTO.getId(),ninjaModel.getId());
        assertEquals(ninjaDTO.getNome(),ninjaModel.getNome());
        assertEquals(ninjaDTO.getEmail(), ninjaModel.getEmail());
        assertEquals(ninjaDTO.getIdade(), ninjaModel.getIdade());
        assertEquals(ninjaDTO.getRank(), ninjaModel.getRank());
        assertEquals(ninjaDTO.getImgurl(), ninjaModel.getImgurl());
        assertEquals(ninjaDTO.getMissoes(), ninjaModel.getMissoes());



    }

    @Test
    void deveConverterModelParaDto() {
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(1L);
        missoesModel.setNomedamissao("Resgatar o Hokage");

        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(1L);
        ninjaModel.setNome("Naruto");
        ninjaModel.setEmail("naruto@email.com");
        ninjaModel.setIdade(17);
        ninjaModel.setRank("Genin");
        ninjaModel.setImgurl("imagem.jpg");
        ninjaModel.setMissoes(missoesModel);

        NinjaDTO ninjaDTO = ninjaMapper.map(ninjaModel);

        assertNotNull(ninjaDTO);

        assertEquals(ninjaModel.getId(), ninjaDTO.getId());
        assertEquals(ninjaModel.getNome(), ninjaDTO.getNome());
        assertEquals(ninjaModel.getEmail(), ninjaDTO.getEmail());
        assertEquals(ninjaModel.getIdade(), ninjaDTO.getIdade());
        assertEquals(ninjaModel.getRank(), ninjaDTO.getRank());
        assertEquals(ninjaModel.getImgurl(), ninjaDTO.getImgurl());
        assertEquals(ninjaModel.getMissoes(), ninjaDTO.getMissoes());
    }
}