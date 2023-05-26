package pe.edu.pucp.tel131.lab3.exception;

public class MalIpException extends Exception{
    @Override
    public String getMessage(){
        return "Ingrese una IP válida";
    }
}
