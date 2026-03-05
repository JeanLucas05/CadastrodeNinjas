package dev.java10x.CadastrodeNinjas.Missoes;

import org.springframework.data.jpa.repository.JpaRepository;
//O JpaRepository simplifica o acesso ao banco
public interface MissoesRepository  extends JpaRepository <MissoesModel ,Long> {
    
}
