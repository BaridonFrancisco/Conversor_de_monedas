package models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public static List<Register> datesEquals(List<Register>listRegister, LocalDate date){
        return listRegister.stream()
                .filter(r-> r.localDateTime.toLocalDate().isEqual(date))
                .toList();

    }

    private String showDate(){
      return String.format("""
                Fecha:%d:%d:%d
                Hora de conversion:%d:%d"""
              ,localDateTime.getYear(),localDateTime.getMonth().getValue(),localDateTime.getDayOfMonth()
      ,localDateTime.getHour(),localDateTime.getMinute());


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
