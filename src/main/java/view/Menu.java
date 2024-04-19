package view;

import models.Currency;
import models.CurrencyType;
import service.CurrencyManager;
import utils.io.IoRegister;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    CurrencyManager currencyManager=new CurrencyManager();
    Scanner scanner=new Scanner(System.in);
    IoRegister io=new IoRegister();
    public Menu() throws IOException {
    }

    public void menuConversion() throws IOException, InterruptedException {
        int op;
        String typeExchange;
        CurrencyType[] typeCurrencies=CurrencyType.values();
        String baseCurrency,targetCurrency;
        BigDecimal bigDecimal;
        do{
            System.out.println("""
                    Bievenido al conversion de monedas
                    1.Mostrar todas las monedas soportadas por el sistema
                    2.Todos los valores cambio de una moneda
                    3.Valor de cambio de una moneda  de 1 exactamente (1usd por ejemplo ) con respecto a la otra
                    4.Convertir un el monto de una  moneda a otra
                    5.Mostrar registros o historial de la conversiones
                    6.Salir""");
            switch (op= scanner.nextInt()){
                case 1:
                    var  currenciesSupported=currencyManager.availableCurrencies();
                    System.out.println("Las monedas soportadas son");
                    currenciesSupported.forEach((k,v)-> System.out.println("Moneda:"+k+" Nombre: "+v));
                    Thread.sleep(3000);
                    break;
                case 2:
                    System.out.println("Ingrese el tipo de moneda para obtener el tipo de cambio");
                    typeExchange=scanner.next().toUpperCase();
                    System.out.println(typeExchange);
                    System.out.println(CurrencyType.USD.name().equals(typeExchange));

                    if(!(checkCurrencyType(typeExchange))) throw new RuntimeException();

                    var result=currencyManager.typeExchange(typeExchange);
                    if(result.conversionRates()!=null && result.currencyName()!=null){
                        result.conversionRates().forEach((k,v)-> System.out.println("Moneda:"+k+" valor: "+v));
                    }
                    Thread.sleep(3000);
                    break;
                case 3:
                    System.out.println("ingrese su moneda base");
                    baseCurrency= scanner.next().toUpperCase();
                    System.out.println("Ingrese a la cual desea convertir");
                    targetCurrency=scanner.next().toUpperCase();
                    if( !(checkCurrencyType(baseCurrency) && checkCurrencyType(targetCurrency))) throw new RuntimeException();
                    var currency=currencyManager.pairConversion(baseCurrency,targetCurrency);
                    System.out.println("Un  1 "+currency.currencyName()+" equivale a resultado: "+currency.rateConversion()+" "+currency.currencyTarget());
                    Thread.sleep(3000);
                    break;
                case 4:
                    System.out.println("ingrese su moneda base");
                    baseCurrency= scanner.next().toUpperCase();
                    System.out.println("Ingrese a la cual desea convertir");
                    targetCurrency=scanner.next().toUpperCase();
                    if( !(checkCurrencyType(baseCurrency) && checkCurrencyType(targetCurrency))) throw new RuntimeException();
                    System.out.println("Ingrese un monto al cual desea convertir");
                    bigDecimal= scanner.nextBigDecimal();
                    Currency currency1=currencyManager.pairConversion(baseCurrency,targetCurrency,bigDecimal);
                    System.out.println(bigDecimal.doubleValue()+" "+currency1.currencyName()+" equivale a un valor de "+currency1.conversionResult()+" "+currency1.currencyTarget());
                    io.writeRegister(currency1,"C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\historial\\registro.txt",bigDecimal);
                    break;
                case 5:

                    break;
                default:
                    break;
            }

        }while(op!=6);
    }

    private boolean checkCurrencyType(String currencyType){
        boolean isPresent=false;
        for(CurrencyType type:CurrencyType.values()){
            if(type.name().equals(currencyType)){
                isPresent=true;
                break;
            }
        }
        return isPresent;
    }
}
