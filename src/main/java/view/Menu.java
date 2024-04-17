package view;

import java.util.Scanner;

public class Menu {
    Scanner scanner=new Scanner(System.in);
    public void menuConversion(){
        int op;
        do{
            System.out.println("""
                    Bievenido al conversion de monedas
                    1.Mostrar todas las monedas soportadas por el sistema
                    2.Todos los valores cambio de una moneda
                    3.Valor de cambio de una moneda y con respecto a la otra
                    4.Convertir un el monto de una devisa a otro.
                    5.Salir
                    """);
            switch (op= scanner.nextInt()){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }

        }while(op!=5);
    }
}
