

import view.Menu;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args)  {
    /*    System.out.println("Bienvenido al conversor de devisas \ncargando menu espere..");
        try {
            new Menu().menuConversion();
        } catch (IOException | InterruptedException e) {
            System.out.println(" ha ocurrido una excepcion"+e.getMessage());
        }*/
        Map<String,String> mapas=new HashMap<>();
        for(int i=0;i<100;i++){
            mapas.put(String.valueOf(i),"Francisco");
        }
        final int[] valor = {0};
        mapas.forEach((k,v)->{
            System.out.print(valor[0] +")"+k +" "+ v+"  ");
            valor[0]++;
            if(valor[0] %10==0){
                System.out.println("\n");
            }

        });

    }
}