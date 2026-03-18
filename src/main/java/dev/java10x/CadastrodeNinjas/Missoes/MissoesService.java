package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }
    //criar missoes
    public MissoesModel criarMisssoes(MissoesModel missoes){
        return missoesRepository.save(missoes);
    }

    //Listar todas as Missoes

    public List<MissoesModel> listarmissoes(){
        return missoesRepository.findAll();
    }

    //Listar Misso por id

    public MissoesModel findByid(Long id){
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.orElse(null);
    }

    public MissoesModel atualizarMissoes(Long id , MissoesModel missoesModel){
        if (missoesRepository.existsById(id)){
            MissoesModel missoes = missoesRepository.findById(id).get();
            missoes.setNomedamissao(missoesModel.getNomedamissao());
            missoes.setDificuldade(missoesModel.getDificuldade());
            missoes.setNinjas(missoesModel.getNinjas());
            return missoesRepository.save(missoes);

        }else {
            return null;
        }
    }

    public String deletarmissoes(Long id){
        if (missoesRepository.existsById(id)){
             missoesRepository.deleteById(id);
             return "Missão deletada com sucesso";
        }else{
            return "Missão  nao encontrada ";
        }
    }

}
