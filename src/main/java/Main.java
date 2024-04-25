
import view.Menu;
import java.io.IOException;



public class Main {
    public static void main(String[] args)  {
        System.out.println("Bienvenido al conversor de devisas \ncargando menu espere..");
        try {
            new Menu().menuConversion();
        } catch (IOException | InterruptedException e) {
            System.out.println(" ha ocurrido una excepcion"+e.getMessage());
        }


    }
}