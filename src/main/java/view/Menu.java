package view;

import models.Currency;
import models.CurrencyType;
import models.Quota;
import models.Register;
import service.CurrencyManager;
import utils.Exceptions.CurrencyException;
import utils.Logger.Logger;
import utils.io.IoRegister;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    CurrencyManager currencyManager;
    Scanner scanner = new Scanner(System.in);
    IoRegister io = new IoRegister();
    private final Logger logger=new Logger.Builder()
            .setPath("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\")
            .setName("log")
            .setMkdir("logs")
            .build();

    public Menu() throws IOException, InterruptedException {
        this.currencyManager = new CurrencyManager();

    }

    public void menuConversion() {
        int op = 0;
        String typeExchange;
        String baseCurrency, targetCurrency;
        int year, month, day;
        String exit;
        BigDecimal bigDecimal;
        do {
            try {
                System.out.println("""
                        Bievenido al conversion de monedas
                        1.Mostrar todas las monedas soportadas por el sistema
                        2.Todos los valores de  cambio para un tipo de moneda en especifico
                        3.Valor de cambio para una moneda respecto a otra ejemplo (1us-1.2eur)
                        4.Convertir un el monto de una  moneda a otra
                        5.Mostrar registros o historial de la conversiones
                        6.Mostrar consultas restantes
                        7.Salir""");
                switch (op = scanner.nextInt()) {
                    case 1:
                        var currenciesSupported = currencyManager.availableCurrencies();
                        if (currenciesSupported == null) {
                            throw new NullPointerException("Las monedas soportadas no esta disponibles");
                        }
                        System.out.println("Las monedas soportadas son");
                        currenciesSupported.forEach((k, v) -> System.out.println("Moneda:" + k + " Nombre: " + v +" \n"));
                        Thread.sleep(3000);
                        break;
                    case 2:
                        System.out.println("Ingrese el tipo de moneda para obtener el tipo de cambio");
                        typeExchange = scanner.next().toUpperCase();
                        System.out.println(typeExchange);
                        System.out.println(CurrencyType.USD.name().equals(typeExchange));

                        if (!(CurrencyType.isCurrencyType(typeExchange))) throw new CurrencyException("La devisa ingresa no es valida");

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
                            throw new CurrencyException("La devisa ingresa no es valida");
                        var currency = currencyManager.pairConversion(baseCurrency, targetCurrency);
                        if (currency.currencyName() == null || currency.rateConversion() == null || currency.currencyTarget() == null) {
                            throw new NullPointerException("respuesta vacia error");
                        }
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
                        if (bigDecimal.doubleValue() < 0.0)
                            throw new NumberFormatException("lo valores del monto no pueden ser negativos");
                        Currency currency1 = currencyManager.pairConversion(baseCurrency, targetCurrency, bigDecimal);
                        if (currency1.conversionResult() == null || currency1.currencyTarget() == null || currency1.currencyName() == null) {
                            throw new NullPointerException("la respueta recibida fue nula");
                        }
                        System.out.println(bigDecimal.doubleValue() + " " + currency1.currencyName() + " equivale a un valor de " + currency1.conversionResult() + " " + currency1.currencyTarget());
                        io.writeRegister(currency1, bigDecimal);
                        break;
                    case 5:
                        //si falla datetime exception
                        System.out.println("rellene los datos de la fecha para que el sistema busque en los registros");
                        System.out.println("Inserte el anio a buscar");
                        year = scanner.nextInt();
                        System.out.println("Inserte el mes a buscar");
                        month = scanner.nextInt();
                        System.out.println("Ingrese una fecha a buscar ");
                        day = scanner.nextInt();
                        var list = io.getAllRegisters();
                        if (!list.isEmpty()) {
                            var listFilter = Register.datesEquals(list, LocalDate.of(year, month, day));
                            listFilter.forEach(System.out::println);
                        }
                        break;
                    case 6:
                        Quota quota = currencyManager.quotaRequest();
                        System.out.println("Consultas restantes " + quota.requestRemaining() + "\n" +
                                "Consultas maximas " + quota.requestMax());
                        break;
                    case 7:
                        System.out.println("Esta seguro que desea salir?Y/N");
                        exit = scanner.next().toUpperCase();
                        if (!(exit.equalsIgnoreCase("Y"))) {
                            op = 0;
                        } else {
                            System.out.println("Saliendo....");
                            Thread.sleep(3000);
                        }
                        break;
                    default:
                        System.out.println("Opcion incorrecta intentelo nuevamente");
                        break;
                }
            } catch (IOException e) {
                System.out.println("ha ocurrido excepcion I/O en el flujo de entrada o salida");
                logger.writeLoggerFile(e);
            } catch (DateTimeException e) {
                System.out.println("No ha ingresado una fecha valida intentelo nuevamente");
            } catch (InterruptedException e) {
                System.out.println("El hilo ha sido interumpido");
                logger.writeLoggerFile(e);
            } catch (NullPointerException e) {
                if (e.getMessage() != null) {
                    System.out.println("Ha ocurriedo un problema" + e.getMessage());
                }
                System.out.println("una variable apunta a un puntero nulo");
                logger.writeLoggerFile(e);
            } catch (CurrencyException e) {
                System.out.println(e.getMessage());
                logger.writeLoggerFile(e);
            } catch (Exception e) {
                System.out.println("Ha ocurrido una excepcion");
                logger.writeLoggerFile(e);
            } finally {
                scanner.nextLine();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.writeLoggerFile(e);
                e.printStackTrace();
                break;
            }
        } while (op != 7);
        scanner.close();
    }

}
