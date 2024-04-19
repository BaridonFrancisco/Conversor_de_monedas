package view;

import models.Currency;
import models.CurrencyType;
import service.CurrencyManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Menu {
    CurrencyManager currencyManager=new CurrencyManager();
    Scanner scanner=new Scanner(System.in);

    public Menu() throws IOException {
    }

    public void menuConversion() throws IOException, InterruptedException {
        int op;
        String typeExchange;
        CurrencyType[] typeCurrencies=CurrencyType.values();
        String baseCurrency,targetCurrency="";
        BigDecimal bigDecimal;
        do{
            System.out.println("""
                    Bievenido al conversion de monedas
                    1.Mostrar todas las monedas soportadas por el sistema
                    2.Todos los valores cambio de una moneda
                    3.Valor de cambio de una moneda  de 1 exactamente (1usd por ejemplo ) con respecto a la otra
                    4.Convertir un el monto de una  moneda a otra
                    5.Mostrar registros o historial de la conversiones
                    6.Salir
                    """);
            switch (op= scanner.nextInt()){
                case 1:
                    var  currenciesSupported=currencyManager.availableCurrencies();
                    System.out.println("Las monedas soportadas son");
                    currenciesSupported.forEach((k,v)-> System.out.println("Moneda:"+k+" Nombre: "+v));
                    Thread.sleep(3000);
                    break;
                case 2:
                    System.out.println("Ingrese el tipo de moneda para obtener el tipo de cambio");
                    typeExchange=scanner.next();
                    var result=currencyManager.typeExchange(typeExchange);

                    for(CurrencyType type:typeCurrencies){
                        if(!type.name().equalsIgnoreCase(typeExchange)) throw new RemoteException();
                    }

                    if(result.conversionRates()!=null && result.currencyName()!=null){
                        System.out.println("Moneda:"+result.currencyName());
                        result.conversionRates().forEach((k,v)-> System.out.println("Moneda:"+k+" valor: "+v));
                    }
                    Thread.sleep(3000);
                    break;
                case 3:
                    System.out.println("ingrese su moneda base");
                    baseCurrency= scanner.next();
                    System.out.println("Ingrese a la cual desea convertir");
                    targetCurrency=scanner.next();
                    var currency=currencyManager.pairConversion(baseCurrency,targetCurrency);
                    System.out.println("Un  1 "+currency.currencyName()+" equivale a resultado: "+currency.rateConversion()+" "+currency.currencyTarget());
                    break;
                case 4:
                    System.out.println("ingrese su moneda base");
                    baseCurrency= scanner.next();
                    System.out.println("Ingrese a la cual desea convertir");
                    targetCurrency=scanner.next();
                    System.out.println("Ingrese un monto al cual desea convertir");
                    bigDecimal= scanner.nextBigDecimal();
                    Currency currency1=currencyManager.pairConversion(baseCurrency,targetCurrency,bigDecimal);

                    break;
                case 5:
                    break;
                default:
                    break;
            }

        }while(op!=5);
    }
}
