package dev.java10x.CadastrodeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {
    // @Autowired constroi um construtor
    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    //Listar todos os ninjas
    public List<NinjaModel> listarninjas(){
        return ninjaRepository.findAll();

        //()save equivale a o INSERT INTO
    }

    public NinjaModel findById(Long id){
        return ninjaRepository.findById(id).orElse(null);
    }


    public NinjaModel cadastrarNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }



}
