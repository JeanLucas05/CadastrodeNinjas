package dev.java10x.CadastrodeNinjas.Ninjas;


import dev.java10x.CadastrodeNinjas.Missoes.MissoesModel;
import org.springframework.stereotype.Service;
import dev.java10x.CadastrodeNinjas.Missoes.MissoesRepository;

import java.util.List;


@Service
public class NinjaService {
    // @Autowired constroi um construtor
    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;
    private final MissoesRepository missoesRepository;


    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper, MissoesRepository missoesRepository) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
        this.missoesRepository = missoesRepository;
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

        NinjaModel ninjaModel = ninjaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ninja nao encontrado!!"));
        if(ninjaDTO.getNome() != null){
            ninjaModel.setNome(ninjaDTO.getNome());

        }
        if (ninjaDTO.getEmail() != null){
            ninjaModel.setEmail(ninjaDTO.getEmail());
        }
        if (ninjaDTO.getIdade() != null ){
            ninjaModel.setIdade(ninjaDTO.getIdade());
        }

        if (ninjaDTO.getRank() != null){
            ninjaModel.setRank(ninjaDTO.getRank());
        }
        if (ninjaDTO.getImgurl() != null){
            ninjaModel.setImgurl(ninjaDTO.getImgurl());
        }
        if (ninjaDTO.getMissoes() != null) {

            MissoesModel missao = missoesRepository
                    .findById(ninjaDTO.getMissoes().getId())
                    .orElseThrow(() -> new RuntimeException("Missao nao encontrada"));

            ninjaModel.setMissoes(missao);
        }

        NinjaModel ninjaSalvo = ninjaRepository.save(ninjaModel);

        return ninjaMapper.map(ninjaSalvo);

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
