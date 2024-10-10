package U2.control.exceptions;

public class ContainerException extends Exception {
    public ContainerException(int id){
        super("Das Member-Object mit der ID "+id+" ist bereits vorhanden!");
    }
}
