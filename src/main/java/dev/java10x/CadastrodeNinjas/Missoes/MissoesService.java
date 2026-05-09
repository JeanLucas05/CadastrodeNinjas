package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Ninjas.NinjaModel;
import dev.java10x.CadastrodeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Service;
import dev.java10x.CadastrodeNinjas.Ninjas.NinjaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;
    private final NinjaRepository ninjaRepository;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper, NinjaRepository ninjaRepository) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
        this.ninjaRepository = ninjaRepository;
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

    public Optional<MissoesDTO> findByid(Long id){
        return missoesRepository.findById(id).map(missoesMapper::map);
    }
    public MissoesDTO atualizarMissoes(Long id, MissoesDTO missoesDTO) {

        MissoesModel missoesModel = missoesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missao nao encontrada"));

        if (missoesDTO.getNomedamissao() != null) {
            missoesModel.setNomedamissao(missoesDTO.getNomedamissao());
        }

        if (missoesDTO.getDificuldade() != null) {
            missoesModel.setDificuldade(missoesDTO.getDificuldade());
        }


        MissoesModel missaoSalva = missoesRepository.save(missoesModel);

        return missoesMapper.map(missaoSalva);
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
