package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Execptions.MissaoNotFoundException;
import dev.java10x.CadastrodeNinjas.Execptions.NinjaNotFoundException;
import dev.java10x.CadastrodeNinjas.Ninjas.NinjaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MissoesServiceTest {
    @Mock
    private MissoesRepository missoesRepository;

    @Mock
    private MissoesMapper missoesMapper;

    @Mock
    private NinjaRepository ninjaRepository;

    @InjectMocks
    private MissoesService missoesService;

    @Test
    void criarMisssoes() {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNomedamissao("Resgatar o hokage");
        missoesDTO.setDificuldade("Teste");

        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setNomedamissao("Resgatr o hokage");
        missoesModel.setDificuldade("Teste");

        MissoesModel salvo = new MissoesModel();
        salvo.setId(1L);
        salvo.setNomedamissao("Resgatar o hokage");
        salvo.setDificuldade("Teste");

        MissoesDTO resposta = new MissoesDTO();
        resposta.setId(1L);
        resposta.setNomedamissao("Resgatar o hokage");
        resposta.setDificuldade("Teste");

        when(missoesMapper.map(missoesDTO))
                .thenReturn(missoesModel);

        when(missoesRepository.save(missoesModel))
                .thenReturn(salvo);

        when(missoesMapper.map(salvo))
                .thenReturn(resposta);

        MissoesDTO resultado = missoesService.criarMisssoes(missoesDTO);
        assertEquals("Resgatar o hokage",resultado.getNomedamissao());
        assertEquals("Teste",resultado.getDificuldade());

        verify(missoesRepository).save(missoesModel);


    }

    @Test
    void deveLancarErroQuandoMissoesNaoExisteAoBuscarPorId(){
        when(missoesRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(MissaoNotFoundException.class,()->missoesService.findByid(1L));
    }

    @Test
    void listarmissoes() {
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setNomedamissao("Resgatar o hokage");
        missoesModel.setDificuldade("Teste");

        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNomedamissao("Resgatar o hokage");
        missoesDTO.setDificuldade("Teste");

        when(missoesRepository.findAll()).thenReturn(List.of(missoesModel));

        when(missoesMapper.map(missoesModel)).thenReturn(missoesDTO);

        List<MissoesDTO> missoesModelList = missoesService.listarmissoes();

        assertEquals(1 , missoesModelList.size());
        assertEquals("Resgatar o hokage", missoesModelList.get(0).getNomedamissao());
    }

    @Test
    void deveBuscarMissaoPorId() {
        MissoesModel missao = new MissoesModel();
        missao.setId(1L);
        missao.setNomedamissao("Resgatar o hokage");
        missao.setDificuldade("Teste");

        MissoesDTO resposta = new MissoesDTO();
        resposta.setId(1L);
        resposta.setNomedamissao("Resgatar o hokage");
        resposta.setDificuldade("Teste");

        when(missoesRepository.findById(1L))
                .thenReturn(Optional.of(missao));

        when(missoesMapper.map(missao))
                .thenReturn(resposta);

        MissoesDTO resultado = missoesService.findByid(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Resgatar o hokage", resultado.getNomedamissao());

        verify(missoesRepository).findById(1L);
    }

    @Test
    void atualizarMissoes() {
        MissoesModel missoesModelexistente = new MissoesModel();
        missoesModelexistente.setId(1L);
        missoesModelexistente.setNomedamissao("Missao antiga");
        missoesModelexistente.setDificuldade("Facil");

        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(1L);
        missoesDTO.setNomedamissao("Resgatar o Hokage");
        missoesDTO.setDificuldade("Teste");

        MissoesModel missoesSalvo = new MissoesModel();
        missoesSalvo.setId(1L);
        missoesSalvo.setNomedamissao("Resgatar o Hokage");
        missoesSalvo.setDificuldade("Teste");

        MissoesDTO resposta = new MissoesDTO();
        resposta.setId(1L);
        resposta.setNomedamissao("Resgatar o Hokage");
        resposta.setDificuldade("Teste");

        when(missoesRepository.findById(1L))
                .thenReturn(Optional.of(missoesModelexistente));

        when(missoesRepository.save(missoesModelexistente))
                .thenReturn(missoesSalvo);

        when(missoesMapper.map(missoesSalvo))
                .thenReturn(resposta);

        MissoesDTO resultado = missoesService.atualizarMissoes(1L,missoesDTO);

        assertEquals("Resgatar o Hokage", resultado.getNomedamissao());
        assertEquals("Teste",resultado.getDificuldade());

        verify(missoesRepository).save(missoesModelexistente);



    }

    @Test
    void deletarmissoes() {
        when(missoesRepository.existsById(1L))
                .thenReturn(true);
        missoesService.deletarmissoes(1L);
        verify(missoesRepository).deleteById(1L);
    }

    @Test
    void deveLancarErroQuandoDeletarMissoesInexistente() {

        when(missoesRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(
                MissaoNotFoundException.class,
                () -> missoesService.deletarmissoes(1L)
        );

        verify(missoesRepository, never())
                .deleteById(anyLong());
    }

    @Test
    void deveLancarErroQuandoAtualizarMissaoInexistente() {
        MissoesDTO dto = new MissoesDTO();
        dto.setNomedamissao("Resgatar o Hokage");
        dto.setDificuldade("Teste");

        when(missoesRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                MissaoNotFoundException.class,
                () -> missoesService.atualizarMissoes(1L, dto)
        );

        verify(missoesRepository, never())
                .save(any(MissoesModel.class));
    }
}