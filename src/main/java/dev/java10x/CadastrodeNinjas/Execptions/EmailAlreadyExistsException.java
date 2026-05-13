package dev.java10x.CadastrodeNinjas.Execptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(){

    }
    public  EmailAlreadyExistsException(String message){
        super(message);
    }
}
