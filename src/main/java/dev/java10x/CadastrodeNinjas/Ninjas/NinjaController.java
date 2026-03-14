package dev.java10x.CadastrodeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NinjaController {
    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas (){
        return "Esta e a minha primeira mensagem nessa rota";
    }
    //criar ninja
    @PostMapping("/ninjas")
    public NinjaModel ninjacreate(@RequestBody NinjaModel ninja){
        return ninjaService.cadastrarNinja(ninja);
    }

    //Buscar todos os ninjas
    @GetMapping("ninjas")
    public List<NinjaModel> listarninjas(){
        return ninjaService.listarninjas();
    }

    //Buscar ninja por id
    @GetMapping("ninjas/{id}")
    public NinjaModel buscarporId(@PathVariable Long id){
        return ninjaService.findById(id);
    }

    //Atualizar Ninja
    @PutMapping("/atualizar")
    public String atualizar(){
        return "Ninja Atualizado";
    }

    //Deletar Ninja
    @DeleteMapping("ninjas/{id}")
    public String deletarNinjaPorid(@PathVariable Long id)
    {
         return ninjaService.apagarNinja(id);
    }





}
