package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MissoesMapperTest {

    private final MissoesMapper missoesMapper = new MissoesMapper();

    @Test
    void deveConverterDtoParaModel(){
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(1L);
        ninjaModel.setNome("Naruto");

        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(1L);
        missoesDTO.setNomedamissao("Resgatr o Hokage");
        missoesDTO.setDificuldade("Dificil");
        missoesDTO.setNinjas(List.of(ninjaModel));

        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        assertNotNull(missoesModel);
        assertEquals(missoesDTO.getId(), missoesModel.getId());
        assertEquals(missoesDTO.getNomedamissao(),missoesModel.getNomedamissao());
        assertEquals(missoesDTO.getDificuldade(),missoesModel.getDificuldade());
        assertEquals(missoesDTO.getNinjas(),missoesModel.getNinjas());
    }

    @Test
    void deveConverterModelParaDto(){
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(1L);
        ninjaModel.setNome("Naruto");

        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(1L);
        missoesModel.setNomedamissao("Resgatr o Hokage");
        missoesModel.setDificuldade("Dificil");
        missoesModel.setNinjas(List.of(ninjaModel));

        MissoesDTO missoesDTO = missoesMapper.map(missoesModel);

        assertNotNull(missoesDTO);

        assertEquals(missoesModel.getId(),missoesDTO.getId());
        assertEquals(missoesModel.getNomedamissao(),missoesDTO.getNomedamissao());
        assertEquals(missoesModel.getDificuldade(),missoesDTO.getDificuldade());
        assertEquals(missoesModel.getNinjas(),missoesDTO.getNinjas());
    }



}