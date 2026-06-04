package dev.java10x.CadastrodeNinjas.Missoes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MissoesController.class)
class MissoesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MissoesService missoesService;

    @Test
    void cadastrarMissoes() throws Exception{
        MissoesDTO entrada = new MissoesDTO();
        entrada.setNomedamissao("Resgatar o Hokage");
        entrada.setDificuldade("Dificil");

        MissoesDTO saida = new MissoesDTO();
        saida.setId(1L);
        entrada.setNomedamissao("Resgatar o Hokage");

        when(missoesService.criarMisssoes(any(MissoesDTO.class))).thenReturn(saida);

        mockMvc.perform(post("/missoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada))
        )
                .andExpect(status().isCreated());

    }

    @Test
    void listarmissoes() throws Exception{
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(1L);
        missoesDTO.setNomedamissao("Resgatr o Hokage");
        missoesDTO.setDificuldade("Dificil");

        when(missoesService.listarmissoes()).thenReturn(List.of(missoesDTO));

        mockMvc.perform(get("/missoes"))
                .andExpect(status().isOk());
    }

    @Test
    void missoesporid() throws Exception {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(1L);
        missoesDTO.setNomedamissao("Resgatar o Hokage");
        missoesDTO.setDificuldade("Dificil");

        when(missoesService.findByid(eq(1L))).thenReturn(missoesDTO);

        mockMvc.perform(get("/missoes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(missoesDTO))
        ).andExpect(status().isOk());
    }

    @Test
    void atualizarMissoes() throws Exception{
        MissoesDTO entrada = new MissoesDTO();
        entrada.setNomedamissao("Apolo11");
        entrada.setDificuldade("Dificil");

        MissoesDTO saida = new MissoesDTO();
        saida.setId(1L);
        saida.setNomedamissao("Resgatar o Gaara");
        saida.setDificuldade("Dificil");

        when(missoesService.atualizarMissoes(eq(1L),any(MissoesDTO.class))).thenReturn(saida);

        mockMvc.perform(patch("/missoes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada))
        ).andExpect(status().isOk());


    }

    @Test
    void deletarMissoes()throws Exception {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(1L);
        missoesDTO.setNomedamissao("Resgatar o Hokage");
        missoesDTO.setDificuldade("Dificil");

        when(missoesService.findByid(eq(1L))).thenReturn(missoesDTO);

        mockMvc.perform(delete("/missoes/1")).andExpect(status().isOk());

        verify(missoesService).deletarmissoes(1L);
    }
}