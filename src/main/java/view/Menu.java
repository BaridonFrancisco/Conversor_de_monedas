package view;

import models.Currency;
import models.CurrencyType;
import models.Quota;
import models.Register;
import service.CurrencyManager;
import utils.io.IoRegister;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    CurrencyManager currencyManager;
    Scanner scanner = new Scanner(System.in);
    IoRegister io = new IoRegister();

    public Menu(String key) throws IOException {
        this.currencyManager = new CurrencyManager(key);
    }

    public void menuConversion() throws IOException, InterruptedException {
        int op = 0;
        String typeExchange;
        String baseCurrency, targetCurrency;
        int anio, mes, dia;
        BigDecimal bigDecimal;
        do {
            try {
                System.out.println("""
                        Bievenido al conversion de monedas
                        1.Mostrar todas las monedas soportadas por el sistema
                        2.Todos los valores de  cambio para un tipo de moneda en especifico
                        3.Valor de cambio de una moneda especifica valuada en 1 exactamente (1usd por ejemplo ) con respecto a la otra
                        4.Convertir un el monto de una  moneda a otra
                        5.Mostrar registros o historial de la conversiones
                        6.Mostrar consultas restantes
                        7.Salir""");
                switch (op = scanner.nextInt()) {
                    case 1:
                        var currenciesSupported = currencyManager.availableCurrencies();
                        System.out.println("Las monedas soportadas son");
                        currenciesSupported.forEach((k, v) -> System.out.println("Moneda:" + k + " Nombre: " + v));
                        Thread.sleep(3000);
                        break;
                    case 2:
                        System.out.println("Ingrese el tipo de moneda para obtener el tipo de cambio");
                        typeExchange = scanner.next().toUpperCase();
                        System.out.println(typeExchange);
                        System.out.println(CurrencyType.USD.name().equals(typeExchange));

                        if (!(CurrencyType.isCurrencyType(typeExchange))) throw new RuntimeException();

                        var result = currencyManager.typeExchange(typeExchange);
                        if (result.conversionRates() != null && result.currencyName() != null) {
                            result.conversionRates().forEach((k, v) -> System.out.println("Moneda:" + k + " valor: " + v));
                        }
                        Thread.sleep(3000);
                        break;
                    case 3:
                        System.out.println("ingrese su moneda base");
                        baseCurrency = scanner.next().toUpperCase();
                        System.out.println("Ingrese a la cual desea convertir");
                        targetCurrency = scanner.next().toUpperCase();
                        if (!(CurrencyType.isCurrencyType(baseCurrency) && CurrencyType.isCurrencyType(targetCurrency)))
                            throw new RuntimeException();
                        var currency = currencyManager.pairConversion(baseCurrency, targetCurrency);
                        System.out.println("Un  1 " + currency.currencyName() + " equivale a resultado: " + currency.rateConversion() + " " + currency.currencyTarget());
                        Thread.sleep(3000);
                        break;
                    case 4:
                        System.out.println("ingrese su moneda base");
                        baseCurrency = scanner.next().toUpperCase();
                        System.out.println("Ingrese a la cual desea convertir");
                        targetCurrency = scanner.next().toUpperCase();
                        if (!(CurrencyType.isCurrencyType(baseCurrency) && CurrencyType.isCurrencyType(targetCurrency)))
                            throw new RuntimeException();
                        System.out.println("Ingrese un monto al cual desea convertir");
                        bigDecimal = scanner.nextBigDecimal();
                        Currency currency1 = currencyManager.pairConversion(baseCurrency, targetCurrency, bigDecimal);
                        System.out.println(bigDecimal.doubleValue() + " " + currency1.currencyName() + " equivale a un valor de " + currency1.conversionResult() + " " + currency1.currencyTarget());
                        io.writeRegister(currency1, "C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\historial\\registro.txt", bigDecimal);
                        break;
                    case 5:
                        //si falla datetime exception
                        System.out.println("rellene los datos de la fecha para que el sistema busque en los registros");
                        System.out.println("Inserte el anio a buscar");
                        anio = scanner.nextInt();
                        System.out.println("Inserte el mes a buscar");
                        mes = scanner.nextInt();
                        System.out.println("Ingrese una fecha a buscar ");
                        dia = scanner.nextInt();
                        var list = io.getAllRegisters("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\historial\\registro.txt");
                        if (!list.isEmpty()) {
                            var listFilter = Register.datesEquals(list, LocalDate.of(anio, mes, dia));
                            listFilter.forEach(System.out::println);
                        }
                        break;
                    case 6:
                        Quota quota = currencyManager.quotaRequest();
                        System.out.println("Consultas restantes " + quota.requestRemaining() + "\n" +
                                "Consultas maximas " + quota.requestMax());
                        break;
                    case 7:
                        System.out.println("Saliendo....");
                        Thread.sleep(3000);
                        break;
                    default:
                        System.out.println("Opcion incorrecta intentelo nuevamente");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (op != 7);
    }

}
