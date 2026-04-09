package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //criar missoes
    public MissoesDTO criarMisssoes(MissoesDTO missoesDTO){
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel = missoesRepository.save(missoesModel);
        return missoesMapper.map(missoesModel);

    }

    //Listar todas as Missoes

    public List<MissoesDTO> listarmissoes(){
        return missoesRepository.findAll().stream().map(missoesMapper::map).toList();
    }

    //Listar Misso por id

    public MissoesDTO findByid(Long id){
        return missoesRepository.findById(id).map(missoesMapper::map).orElse(null);
    }

    public MissoesDTO atualizarMissoes(Long id , MissoesDTO missoesDTO){
        if (missoesRepository.existsById(id)){
            MissoesModel missoes = missoesRepository.findById(id).get();
            missoes.setNomedamissao(missoesDTO.getNomedamissao());
            missoes.setDificuldade(missoesDTO.getDificuldade());
            missoes.setNinjas(missoesDTO.getNinjas());
            missoes = missoesRepository.save(missoes);

            return missoesMapper.map(missoes);

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
