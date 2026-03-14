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
    @PostMapping("/ninjas/create")
    public NinjaModel ninjacreate(@RequestBody NinjaModel ninja){
        return ninjaService.cadastrarNinja(ninja);
    }

    //Buscar todos os ninjas
    @GetMapping("ninja/all")
    public List<NinjaModel> listarninjas(){
        return ninjaService.listarninjas();
    }

    //Buscar ninja por id
    @GetMapping("ninja/{id}")
    public NinjaModel findById(@PathVariable Long id){
        return ninjaService.findById(id);
    }

    //Atualizar Ninja
    @PutMapping("/atualizar")
    public String atualizar(){
        return "Ninja Atualizado";
    }

    //Deletar Ninja
    @DeleteMapping("/delete")
    public String deleteforid(){
        return "Ninja Deletado";
    }





}
