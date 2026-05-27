package dev.java10x.CadastrodeNinjas;

import dev.java10x.CadastrodeNinjas.Execptions.MissaoNotFoundException;
import dev.java10x.CadastrodeNinjas.Execptions.NinjaNotFoundException;
import dev.java10x.CadastrodeNinjas.Missoes.MissoesDTO;
import dev.java10x.CadastrodeNinjas.Missoes.MissoesService;
import dev.java10x.CadastrodeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastrodeNinjas.Ninjas.NinjaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static java.lang.Long.MAX_VALUE;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
class CadastrodeNinjasApplicationTests {

    @Autowired
    private MissoesService missoesService;
    @Autowired
    private NinjaService ninjaService;

    @Test
    void contextLoads() {
    }

    @Test
    void insertMissioes() {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNomedamissao("TESTE");
        missoesDTO.setDificuldade("TESTE");

        MissoesDTO c = missoesService.criarMisssoes(missoesDTO);

        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);

        MissoesDTO op = missoesService.findByid(id);

        assertNotNull(op);
        assertEquals("TESTE", op.getNomedamissao());
        assertEquals("TESTE", op.getDificuldade());

        missoesService.deletarmissoes(id);

        assertThrows(
                MissaoNotFoundException.class,
                () -> missoesService.findByid(id)
        );


    }


    @Test
    void listarMissoes(){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNomedamissao("TESTE");
        missoesDTO.setDificuldade("TESTE");

        MissoesDTO salva = missoesService.criarMisssoes(missoesDTO);

        assertFalse(missoesService.listarmissoes().isEmpty());

        missoesService.deletarmissoes(salva.getId());
        assertThrows(
                MissaoNotFoundException.class,
                ()->missoesService.findByid(salva.getId())
        );

    }


    @Test
    void atualizarMissoes() {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNomedamissao("TESTE");
        missoesDTO.setDificuldade("TESTE");
        MissoesDTO salva = missoesService.criarMisssoes(missoesDTO);

        MissoesDTO missoesAtualizada = new MissoesDTO();
        missoesAtualizada.setNomedamissao("TESTE_ATUALIZADO");
        missoesAtualizada.setDificuldade("TESTE_ATUALIZADO");

        MissoesDTO resultado = missoesService.atualizarMissoes(salva.getId(), missoesAtualizada);
        assertEquals("TESTE_ATUALIZADO", resultado.getNomedamissao());
        assertEquals("TESTE_ATUALIZADO", resultado.getDificuldade());
        missoesService.deletarmissoes(resultado.getId());

        assertThrows(
                MissaoNotFoundException.class,
                () -> missoesService.findByid(resultado.getId())
        );
    }

    @Test
    void buscarMissoesInexistente(){
        Long idnexistente = MAX_VALUE;
        assertThrows(
                MissaoNotFoundException.class,
                () -> missoesService.findByid(idnexistente)
        );
    }

    @Test
    void deletarMissoesInexistente(){
        Long idnexistente = MAX_VALUE;
        assertThrows(
                MissaoNotFoundException.class,
                ()-> missoesService.deletarmissoes(idnexistente)
        );
    }

    @Test
    void atualizarMissoesInexistente(){
        Long idnexistente = MAX_VALUE;
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNomedamissao("TESTE");
        missoesDTO.setDificuldade("TESTE");
        assertThrows(
                MissaoNotFoundException.class,
                ()->missoesService.atualizarMissoes(idnexistente,missoesDTO)
        );
    }






    //NINJAS TESTES


    @Test
    void insertNinjas(){
        NinjaDTO ninjaDTO =  new NinjaDTO ();
        ninjaDTO.setNome("TESTE");
        ninjaDTO.setEmail("teste@gmail.com");
        ninjaDTO.setIdade(11);
        ninjaDTO.setRank("TESTE");
        ninjaDTO.setImgurl("www.imgutl/test");

        NinjaDTO n = ninjaService.cadastrarNinja(ninjaDTO);

        assertNotNull(n);

        Long id = n.getId();
        assertNotNull(id);

        NinjaDTO op = ninjaService.buscarPorid(id);
        assertNotNull(op);

        assertEquals("TESTE",op.getNome());
        assertEquals("teste@gmail.com",op.getEmail());
        assertEquals(11 , op.getIdade());
        assertEquals("TESTE",op.getRank());
        assertEquals("www.imgutl/test",op.getImgurl());

        ninjaService.deletarninja(id);
        assertThrows(
                NinjaNotFoundException.class,
                () ->ninjaService.buscarPorid(id)
        );

    }

    @Test
    void listarninjas(){
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome("ninjaTeste");
        ninjaDTO.setEmail("teste@gmail.com");
        ninjaDTO.setIdade(11);
        ninjaDTO.setRank("RankTeste");
        ninjaDTO.setImgurl("ImgTeste");

        NinjaDTO salva = ninjaService.cadastrarNinja(ninjaDTO);

        NinjaDTO ninjaAtualizado = new NinjaDTO();
        ninjaAtualizado.setNome("ninjaatualizado");
        ninjaAtualizado.setEmail("atualizado@email.com");
        ninjaAtualizado.setIdade(12);
        ninjaAtualizado.setRank("RankTeste");
        ninjaAtualizado.setImgurl("ImgAtualizada");

        NinjaDTO resultado = ninjaService.atualizar(salva.getId(), ninjaAtualizado);
        assertEquals("ninjaatualizado",resultado.getNome());
        assertEquals("atualizado@email.com",resultado.getEmail());
        assertEquals(12,resultado.getIdade());
        assertEquals("RankTeste",resultado.getRank());
        assertEquals("ImgAtualizada",resultado.getImgurl());
        ninjaService.deletarninja(resultado.getId());

        assertThrows(
                NinjaNotFoundException.class,
                () -> ninjaService.buscarPorid(resultado.getId())
        );


    }

    @Test
    void listarNinjas(){
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome("ninjaTeste");
        ninjaDTO.setEmail("teste@gmail.com");
        ninjaDTO.setIdade(11);
        ninjaDTO.setRank("RankTeste");
        ninjaDTO.setImgurl("ImgTeste");

        NinjaDTO salva = ninjaService.cadastrarNinja(ninjaDTO);
        assertFalse(ninjaService.listarninjas().isEmpty());
        ninjaService.deletarninja(salva.getId());
        assertThrows(
                NinjaNotFoundException.class,
                () ->ninjaService.buscarPorid(salva.getId())
        );
    }
    @Test
    void buscarNinjaInexistente(){
        Long idnexistente = MAX_VALUE;
        assertThrows(
                NinjaNotFoundException.class,
                () -> ninjaService.buscarPorid(idnexistente)
        );
    }

    @Test
    void ataulizarNinjaInexiste(){
        Long idnexistente = MAX_VALUE;
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome("ninjaTeste");
        ninjaDTO.setEmail("teste@gmail.com");
        ninjaDTO.setIdade(11);
        ninjaDTO.setRank("RankTeste");
        ninjaDTO.setImgurl("ImgTeste");
        assertThrows(
                NinjaNotFoundException.class,
                () -> ninjaService.atualizar(idnexistente,ninjaDTO)
        );

    }

    @Test
    void deletarNinjaInexistente(){
        Long idnexistente = MAX_VALUE;
        assertThrows(
                NinjaNotFoundException.class,
                () -> ninjaService.deletarninja(idnexistente)
        );
    }

}