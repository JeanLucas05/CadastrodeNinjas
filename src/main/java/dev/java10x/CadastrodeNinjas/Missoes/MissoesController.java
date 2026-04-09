package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.ApiResponse;
import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class MissoesController {
    private final MissoesService missoesService;

    //Criar Missoes
    @PostMapping("missoes")
    public ResponseEntity<ApiResponse> cadastrarMissoes(@RequestBody MissoesDTO missoesDTO){
        MissoesDTO missoesDTO1 =  missoesService.criarMisssoes(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Missao Criada com sucesso!!",missoesDTO1));
    }
    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }
    @GetMapping("missoes")
    public ResponseEntity<List<MissoesDTO>> listarmissoes(){
       return ResponseEntity.ok(missoesService.listarmissoes());
    }
    @GetMapping("missoes/{id}")
    public ResponseEntity<ApiResponse> missoesporid(@PathVariable Long id ){
        MissoesDTO missoes = missoesService.findByid(id);
        if (missoes != null){
            return ResponseEntity.ok(new ApiResponse("Missao encontrada!",missoes));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Missao nao encontrada!!",null));
        }
    }


    @PutMapping("missoes/{id}")
    public ResponseEntity<ApiResponse> atualizarMissoes(@PathVariable Long id ,@RequestBody MissoesDTO missoesDTO ){
        if (missoesService.findByid(id) !=null ){
            MissoesDTO missoesAtualizada = missoesService.atualizarMissoes(id, missoesDTO);
            return ResponseEntity.status(200).body(new ApiResponse("Missao Atualizada com Sucesso!!", missoesAtualizada));

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Missao nao encontrada!!",null));
        }

    }
    //Deletar Missoes
    @DeleteMapping("missoes/{id}")
    public ResponseEntity<ApiResponse> deletarMissoes(@PathVariable Long id){
        if (missoesService.findByid(id) != null){
            missoesService.deletarmissoes(id);
            return ResponseEntity.ok().body(new ApiResponse("Missao Deletada com sucesso!!",null));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Missao nao encontrada",null));
        }

    }

}
