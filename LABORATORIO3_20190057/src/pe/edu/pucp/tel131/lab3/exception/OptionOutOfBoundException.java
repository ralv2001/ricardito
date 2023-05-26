package pe.edu.pucp.tel131.lab3.exception;

public class OptionOutOfBoundException extends Exception{
    @Override
    public String getMessage(){
        return "Ingresar una opción válida para el Menú";
    }
}
