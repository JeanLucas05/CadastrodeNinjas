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
    public NinjaDTO ninjacreate(@RequestBody NinjaDTO ninja){

        return ninjaService.cadastrarNinja(ninja);
    }

    //Buscar todos os ninjas
    @GetMapping("ninjas")
    public List<NinjaDTO> listarninjas(){
        return ninjaService.listarninjas();
    }

    @GetMapping("ninjas/{id}")
    public NinjaDTO buscarporid(@PathVariable Long id){
        return ninjaService.buscarPorid(id);
    }


    //Atualizar Ninja
    @PutMapping("ninjas/{id}")
    public NinjaDTO atualizar(@PathVariable Long id , @RequestBody NinjaDTO ninjaDTO){
        return ninjaService.atualizar(id, ninjaDTO);
    }

    //Deletar Ninja
    @DeleteMapping("ninjas/{id}")
    public String deletarNinjaPorid(@PathVariable Long id)
    {
         return ninjaService.apagarNinja(id);
    }





}
