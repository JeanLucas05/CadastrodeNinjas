package dev.java10x.CadastrodeNinjas.Missoes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping
public class MissoesController {
    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }
    @GetMapping("missoes/all")
    public List<MissoesModel> listarmissoes(){
        return missoesService.listarmissoes();
    }
    @GetMapping("missoes/{id}")
    public MissoesModel findById(@PathVariable Long id ){
        return missoesService.findByid(id);
    }
}
