package dev.java10x.CadastrodeNinjas.Missoes;

import dev.java10x.CadastrodeNinjas.Execptions.MissaoNotFoundException;

import org.springframework.stereotype.Service;


import java.util.List;


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
        return missoesRepository.findById(id).map(missoesMapper::map)
                .orElseThrow(() -> new MissaoNotFoundException("Missão não encontrada "));
    }
    public MissoesDTO atualizarMissoes(Long id, MissoesDTO missoesDTO) {

        MissoesModel missoesModel = missoesRepository.findById(id)
                .orElseThrow(() -> new MissaoNotFoundException("Missao nao encontrada"));

        if (missoesDTO.getNomedamissao() != null) {
            missoesModel.setNomedamissao(missoesDTO.getNomedamissao());
        }

        if (missoesDTO.getDificuldade() != null) {
            missoesModel.setDificuldade(missoesDTO.getDificuldade());
        }


        MissoesModel missaoSalva = missoesRepository.save(missoesModel);

        return missoesMapper.map(missaoSalva);
    }

    public void deletarmissoes(Long id){
        if (!missoesRepository.existsById(id)){
            throw new MissaoNotFoundException("Missão não encontrada com id: " + id);
        }
        missoesRepository.deleteById(id);
    }

}
