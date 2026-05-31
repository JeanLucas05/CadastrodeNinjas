package dev.java10x.CadastrodeNinjas.Ninjas;

import dev.java10x.CadastrodeNinjas.Execptions.EmailAlreadyExistsException;
import dev.java10x.CadastrodeNinjas.Execptions.NinjaNotFoundException;
import dev.java10x.CadastrodeNinjas.Missoes.MissoesRepository;
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
class NinjaServiceTest {
    @Mock
    private NinjaRepository ninjaRepository;

    @Mock
    private NinjaMapper ninjaMapper;

    @Mock
    private MissoesRepository missoesRepository;

    @InjectMocks
    private NinjaService ninjaService;

    @Test
    void cadastrarNinja() {

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome("Naruto");
        ninjaDTO.setEmail("naruto@gmail.com");
        ninjaDTO.setIdade(11);
        ninjaDTO.setRank("Hokage");
        ninjaDTO.setImgurl("naruto/image.com");

        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setNome("Naruto");
        ninjaModel.setEmail("naruto@gmail.com");
        ninjaModel.setIdade(11);
        ninjaModel.setRank("Hokage");
        ninjaModel.setImgurl("naruto/image.com");

        NinjaModel salvo = new NinjaModel();
        salvo.setId(1L);
        salvo.setNome("Naruto");
        salvo.setEmail("naruto@gmail.com");
        salvo.setIdade(11);
        salvo.setRank("Hokage");

        NinjaDTO resposta = new NinjaDTO();
        resposta.setId(1L);
        resposta.setNome("Naruto");
        resposta.setEmail("naruto@gmail.com");
        resposta.setIdade(11);
        resposta.setRank("Hokage");

        when(ninjaRepository.existsByEmail("naruto@gmail.com"))
                .thenReturn(false);

        when(ninjaMapper.map(ninjaDTO))
                .thenReturn(ninjaModel);

        when(ninjaRepository.save(ninjaModel))
                .thenReturn(salvo);

        when(ninjaMapper.map(salvo))
                .thenReturn(resposta);

        NinjaDTO resultado = ninjaService.cadastrarNinja(ninjaDTO);

        assertEquals("Naruto", resultado.getNome());

        verify(ninjaRepository).save(ninjaModel);



    }

    @Test
    void listarninjas() {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(1L);
        ninjaModel.setNome("Kakashi");

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(1L);
        ninjaDTO.setNome("Kakashi");

        when(ninjaRepository.findAll()).thenReturn(List.of(ninjaModel));
        when(ninjaMapper.map(ninjaModel)).thenReturn(ninjaDTO);

        List <NinjaDTO> resultado = ninjaService.listarninjas();

        assertEquals(1,resultado.size());
        assertEquals("Kakashi",resultado.get(0).getNome());
    }

    @Test
    void  deveLancarErroQuandoEmailJaExisteAoCadastrar(){
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setEmail("naruto@gmail.com");
        when(ninjaRepository.existsByEmail(ninjaDTO.getEmail()))
                .thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class ,
                ()-> ninjaService.cadastrarNinja(ninjaDTO));
        verify(ninjaRepository, never()).save(any());
    }

    @Test
    void buscarPorid() {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(1L);
        ninjaModel.setNome("Naruto");

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(1L);
        ninjaDTO.setNome("Naruto");

        when(ninjaRepository.findById(1L))
                .thenReturn(Optional.of(ninjaModel));

        when(ninjaMapper.map(ninjaModel))
                .thenReturn(ninjaDTO);

        NinjaDTO resultado = ninjaService.buscarPorid(1L);

        assertNotNull(resultado);

        assertEquals(1L , resultado.getId());
        assertEquals("Naruto",resultado.getNome());
    }
    @Test
    void deveLancarErroQuandoNinjaNaoExisteAoBuscarPorId(){
        when(ninjaRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(NinjaNotFoundException.class,()->ninjaService.buscarPorid(1L));
    }

    @Test
    void atualizar() {
        NinjaModel ninjaExistente = new NinjaModel();
        ninjaExistente.setId(1L);
        ninjaExistente.setNome("Naruto");
        ninjaExistente.setEmail("naruto@gmail.com");

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setNome("Naruto Uzumaki");
        ninjaDTO.setEmail("uzumaki@gmail.com");
        ninjaDTO.setIdade(17);
        ninjaDTO.setRank("Hokage");

        NinjaModel ninjaSalvo = new NinjaModel();
        ninjaSalvo.setId(1L);
        ninjaSalvo.setNome("Naruto Uzumaki");
        ninjaSalvo.setEmail("uzumaki@gmail.com");
        ninjaSalvo.setIdade(17);
        ninjaSalvo.setRank("Hokage");

        NinjaDTO resposta = new NinjaDTO();
        resposta.setId(1L);
        resposta.setNome("Naruto Uzumaki");
        resposta.setEmail("uzumaki@gmail.com");
        resposta.setIdade(17);
        resposta.setRank("Hokage");

        when(ninjaRepository.findById(1L))
                .thenReturn(Optional.of(ninjaExistente));

        when(ninjaRepository.existsByEmailAndIdNot("uzumaki@gmail.com", 1L))
                .thenReturn(false);

        when(ninjaRepository.save(ninjaExistente))
                .thenReturn(ninjaSalvo);

        when(ninjaMapper.map(ninjaSalvo))
                .thenReturn(resposta);

        NinjaDTO resultado = ninjaService.atualizar(1L, ninjaDTO);

        assertEquals("Naruto Uzumaki", resultado.getNome());
        assertEquals("uzumaki@gmail.com", resultado.getEmail());
        assertEquals(17, resultado.getIdade());

        verify(ninjaRepository).save(ninjaExistente);
    }

    @Test
    void  deveLancarErroQuandoAtualizarNinjaInexistente(){
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(1L);
        ninjaDTO.setNome("Naruto");

        when(ninjaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(NinjaNotFoundException.class,()->{
            ninjaService.atualizar(1L,ninjaDTO);

        });

        verify(ninjaRepository, never()).save(any());
    }
    @Test
    void deveLancarErroQuandoAtualizarComEmailJaExistente() {

        NinjaModel ninjaExistente = new NinjaModel();
        ninjaExistente.setId(1L);
        ninjaExistente.setEmail("naruto@gmail.com");

        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setEmail("sasuke@gmail.com");

        when(ninjaRepository.findById(1L))
                .thenReturn(Optional.of(ninjaExistente));

        when(ninjaRepository.existsByEmailAndIdNot(
                "sasuke@gmail.com", 1L))
                .thenReturn(true);

        assertThrows(
                EmailAlreadyExistsException.class,
                () -> ninjaService.atualizar(1L, ninjaDTO)
        );

        verify(ninjaRepository, never()).save(any());
    }


    @Test
    void deletarninja() {
        when(ninjaRepository.existsById(1L))
                .thenReturn(true);
        ninjaService.deletarninja(1L);
        verify(ninjaRepository).deleteById(1L);
    }

    @Test
    void  deveLancarErroQuandoDeletarNinjaInexistente(){
        when(ninjaRepository.existsById(1L)).
                thenReturn(false);
        assertThrows(NinjaNotFoundException.class,()-> ninjaService.deletarninja(1L)
                );
        verify(ninjaRepository, never()).deleteById(anyLong());
    }
}