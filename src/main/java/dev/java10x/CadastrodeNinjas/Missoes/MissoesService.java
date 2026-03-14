package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MissoesService {
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }
    //Listar todas as Missoes
    public List<MissoesModel> listarmissoes(){
        return missoesRepository.findAll();
    }
    //Listar Misso por id
    public MissoesModel findByid(Long id){
        return missoesRepository.findById(id).orElse(null);
    }
}
