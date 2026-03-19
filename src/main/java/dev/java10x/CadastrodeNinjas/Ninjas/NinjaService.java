package dev.java10x.CadastrodeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
    // @Autowired constroi um construtor
    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;


    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //Cadastrar novo ninja
    public NinjaDTO cadastrarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }
    //Listar todos os ninjas
    public List<NinjaDTO> listarninjas(){
        return ninjaRepository.findAll().stream().map(ninjaMapper::map).toList();


    }
    public NinjaDTO buscarPorid(Long id ){
        return ninjaRepository.findById(id).map(ninjaMapper::map).orElse(null);


    }

    //Atualizar Ninja
    public NinjaDTO atualizar(Long id, NinjaDTO ninjaDTO){
        if(ninjaRepository.existsById(id)){
            NinjaModel ninja = ninjaRepository.findById(id).get();
            ninja.setNome(ninjaDTO.getNome());
            ninja.setEmail(ninjaDTO.getEmail());
            ninja.setIdade(ninjaDTO.getIdade());
            ninja.setMissoes(ninjaDTO.getMissoes());
            ninja.setRank(ninjaDTO.getRank());
            ninja.setImgurl(ninjaDTO.getImgurl());
            ninja = ninjaRepository.save(ninja);

            return ninjaMapper.map(ninja);

        }else {
            return null;
        }
    }



    //Deleta ninja por id
    public String apagarNinja(Long id){
        if(ninjaRepository.existsById(id)){
            ninjaRepository.deleteById(id);
            return "Ninja deletada com sucesso";

        }else{
            return "Ninja  nao encontrado ";
        }
    }



}
