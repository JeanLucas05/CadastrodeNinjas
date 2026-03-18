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
    public MissoesModel cadastrarMissoes(@RequestBody MissoesModel missoes){
        return missoesService.criarMisssoes(missoes);
    }
    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }
    @GetMapping("missoes")
    public List<MissoesModel> listarmissoes(){
        return missoesService.listarmissoes();
    }
    @GetMapping("missoes/{id}")
    public MissoesModel missoesporid(@PathVariable Long id ){
        return missoesService.findByid(id);
    }


    @PutMapping("missoes/{id}")
    public MissoesModel atualizarMissoes(@PathVariable Long id ,@RequestBody MissoesModel missoes ){
        return missoesService.atualizarMissoes(id, missoes);
    }
    //Deletar Missoes
    @DeleteMapping("missoes/{id}")
    public String deletarMissoes(@PathVariable Long id){
        return missoesService.deletarmissoes(id);
    }

}
