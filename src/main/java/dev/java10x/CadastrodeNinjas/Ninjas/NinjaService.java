package dev.java10x.CadastrodeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
    // @Autowired constroi um construtor
    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }
    //Cadastrar novo ninja
    public NinjaModel cadastrarNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }
    //Listar todos os ninjas
    public List<NinjaModel> listarninjas(){
        return ninjaRepository.findAll();


    }
    // Procurar pelo {id} do ninja
    public NinjaModel findById(Long id){
        Optional<NinjaModel> ninjaporId = ninjaRepository.findById(id);
        return ninjaporId.orElse(null);
    }

    //Atualizar Ninja



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
