package dev.java10x.CadastrodeNinjas.Execptions;

public class MissaoNotFoundException extends RuntimeException{
    public MissaoNotFoundException(){

    }

    public MissaoNotFoundException(String message){
        super(message);
    }
}
