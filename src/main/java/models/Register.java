package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Register {
    LocalDateTime localDateTime;
    private String currencyBase;
    private String currencyTarget;
    private BigDecimal result;
    private BigDecimal amount;

    public Register(LocalDateTime localDateTime, String currencyBase, String currencyTarget, BigDecimal result, BigDecimal amount) {
        this.localDateTime = localDateTime;
        this.currencyBase = currencyBase;
        this.currencyTarget = currencyTarget;
        this.result = result;
        this.amount = amount;
    }

    private String showDate(){
      return String.format("""
                Anio:%d
                Mes:%d
                Dia:%d
                Hora:%d
                Minuto:%d
                Fecha completa:%d:%d:%d""",localDateTime.getYear(),localDateTime.getMonth().getValue(),
                localDateTime.getDayOfMonth(), localDateTime.getHour(),localDateTime.getMinute(),
              localDateTime.getYear(),localDateTime.getMonth().getValue(),localDateTime.getDayOfMonth());

    }

    @Override
    public String toString() {
        return  "******Registro******" + "\n" +
                "Fecha "+showDate() + "\n"+
                "Moneda base ->" + currencyBase + '\n' +
                "Moneda a convertir ->" + currencyTarget + '\n' +
                "Valor ingresado ->" + amount + "\n"+
                "Resultado ->" + result+"\n" +
                "--------------------------";
    }
}
