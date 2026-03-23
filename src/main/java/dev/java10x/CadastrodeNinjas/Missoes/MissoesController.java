package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class MissoesController {
    private MissoesService missoesService;

    //Criar Missoes
    @PostMapping("missoes")
    public MissoesDTO cadastrarMissoes(@RequestBody MissoesDTO missoesDTO){
        return missoesService.criarMisssoes(missoesDTO);
    }
    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }
    @GetMapping("missoes")
    public List<MissoesDTO> listarmissoes(){
        return missoesService.listarmissoes();
    }
    @GetMapping("missoes/{id}")
    public MissoesDTO missoesporid(@PathVariable Long id ){
        return missoesService.findByid(id);
    }


    @PutMapping("missoes/{id}")
    public MissoesDTO atualizarMissoes(@PathVariable Long id ,@RequestBody MissoesDTO missoesDTO ){
        return missoesService.atualizarMissoes(id, missoesDTO);
    }
    //Deletar Missoes
    @DeleteMapping("missoes/{id}")
    public String deletarMissoes(@PathVariable Long id){
        return missoesService.deletarmissoes(id);
    }

}
