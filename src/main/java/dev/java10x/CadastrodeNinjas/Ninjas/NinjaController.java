package dev.java10x.CadastrodeNinjas.Ninjas;

import dev.java10x.CadastrodeNinjas.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
@Tag(name = "Ninjas", description = "Gerenciamento de ninjas")
public class NinjaController {
    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @Operation(summary = "Retorna uma mensagem de boas-vindas da API")
    @GetMapping("/boasVindas")
    public String boasVindas (){
        return "Esta e a minha primeira mensagem nessa rota";
    }
    //criar ninja
    @Operation(summary = "Cadastra um novo Ninja ")
    @PostMapping("/ninjas")
    public ResponseEntity<ApiResponse> ninjacreate(@RequestBody NinjaDTO ninja){

        NinjaDTO ninjaDTO = ninjaService.cadastrarNinja(ninja);
        ApiResponse apiResponse = new ApiResponse("Ninja criado com sucesso!",ninjaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @Operation(summary = "Lista todos os Ninjas")
    //Buscar todos os ninjas
    @GetMapping("ninjas")
    public ResponseEntity<List<NinjaDTO>> listarninjas(){

        return ResponseEntity.ok(ninjaService.listarninjas());
    }

    @Operation(summary = "Busca um Ninja pelo ID")
    @GetMapping("ninjas/{id}")
    public ResponseEntity <?> buscarporid(@PathVariable Long id){
        NinjaDTO ninjaDTO = ninjaService.buscarPorid(id);

        if (ninjaDTO != null){
            return ResponseEntity.ok(new ApiResponse("Ninja encontrado",ninjaDTO) );
        }else{
            return ResponseEntity.status(404).body(new ApiResponse("Ninja nao encontrado",null));
        }
    }

    @Operation(summary = "Atualiza parcialmente um ninja existente")
    //Atualizar Ninja
    @PatchMapping("ninjas/{id}")
    public ResponseEntity<ApiResponse> atualizar(@PathVariable Long id , @RequestBody NinjaDTO ninjaDTO){
        NinjaDTO ninjaatualizada = ninjaService.atualizar(id,ninjaDTO);

        return ResponseEntity.ok().body(new ApiResponse("Ninja Atualizado!!",ninjaatualizada));

    }

    @Operation(summary = "Deleta um Ninja Pelo ID")
    //Deletar Ninja
    @DeleteMapping("ninjas/{id}")
    public ResponseEntity<ApiResponse> deletarNinjaPorid(@PathVariable Long id)
    {
        if (ninjaService.buscarPorid(id) !=null){
            ninjaService.deletarninja(id);
            return ResponseEntity.ok().body(new ApiResponse("Ninja deletado com sucesso" , null) );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Ninja nao encontrado",null));
        }

    }





}
