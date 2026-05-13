package dev.java10x.CadastrodeNinjas.Execptions;

public class NinjaNotFoundException extends RuntimeException{
    public NinjaNotFoundException() {
    }

    public NinjaNotFoundException(String message) {
        super(message);
    }


}
