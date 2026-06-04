package dev.java10x.CadastrodeNinjas.Ninjas;

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

@WebMvcTest(NinjaController.class)
class NinjaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private NinjaService ninjaService;

    @Test
    void boasVindas() throws Exception {
        mockMvc.perform(get("/boasVindas"))
                .andExpect(status().isOk())
                .andExpect(content().string("Esta e a minha primeira mensagem nessa rota"));
    }

    @Test
    void ninjacreate() throws Exception {
        NinjaDTO entrada = new NinjaDTO();
        entrada.setNome("Naruto");
        entrada.setEmail("naruto@gmail.com");
        entrada.setIdade(11);

        NinjaDTO saida = new NinjaDTO();
        saida.setId(1L);
        saida.setNome("Naruto");
        saida.setEmail("naruto@gmail.com");
        saida.setIdade(11);

        when(ninjaService.cadastrarNinja(any(NinjaDTO.class))).thenReturn(saida);

        mockMvc.perform(post("/ninjas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated());
    }

    @Test
    void listarninjas()throws Exception{
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(1L);
        ninjaDTO.setNome("Naurto");

        when(ninjaService.listarninjas()).thenReturn(List.of(ninjaDTO));

        mockMvc.perform(get("/ninjas")


        )
                .andExpect(status().isOk());

    }

    @Test
    void buscarporid() throws Exception{
        NinjaDTO ninjaDTO =  new NinjaDTO();
        ninjaDTO.setId(1L);
        ninjaDTO.setNome("Naurto");
        ninjaDTO.setEmail("naurto@gmail.com");

        when(ninjaService.buscarPorid(eq(1L))).thenReturn(ninjaDTO);

        mockMvc.perform(get("/ninjas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ninjaDTO))
        )
                .andExpect(status().isOk());
    }

    @Test
    void atualizar() throws Exception {

        NinjaDTO entrada = new NinjaDTO();
        entrada.setNome("Naruto");
        entrada.setEmail("naruto@gmail.com");
        entrada.setIdade(11);

        NinjaDTO saida = new NinjaDTO();
        saida.setId(1L);
        saida.setNome("Naruto");
        saida.setEmail("naruto@gmail.com");
        saida.setIdade(11);

        when(ninjaService.atualizar(eq(1L), any(NinjaDTO.class)))
                .thenReturn(saida);

        mockMvc.perform(
                        patch("/ninjas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entrada))
                )
                .andExpect(status().isOk());
    }

    @Test
    void deletarNinjaPorid() throws Exception{
        NinjaDTO ninja = new NinjaDTO();
        ninja.setId(1L);
        ninja.setNome("Naruto");
        ninja.setEmail("naruto@gmail.com");

        when(ninjaService.buscarPorid(1L))
                .thenReturn(ninja);
        mockMvc.perform(
                delete("/ninjas/1"))
                .andExpect(status().isOk());
        verify(ninjaService).deletarninja(1L);



    }
}