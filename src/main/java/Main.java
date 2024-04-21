
import models.CurrencyType;

import models.Register;
import utils.io.IoRegister;
import view.Menu;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Retrieve all environment variables
        Map<String, String> env_var = System.getenv();
        var operatingSystem = System.getenv("api-key");
        System.out.println(operatingSystem);
        // Loop through all environment variables
        /*for (String envName : env_var.keySet()) {
            // Print environment variable name and value to console
            System.out.format("%s=%s", envName, env_var.get(envName));
            System.out.println();
        }*/

        new Menu().menuConversion();

        Register r=new Register(LocalDateTime.now(),"USD","ARS",new BigDecimal("0.0"),new BigDecimal("0.0"));
        IoRegister writer=new IoRegister();
        var resgister=writer.getAllRegisters();
        /*for(Register re:resgister){
            System.out.println(re);
        }*/
        var res=Register.datesEquals(resgister, LocalDate.of(2023,5,21));
        System.out.println(resgister.get(6).getLocalDateTime().toLocalDate());
        System.out.println(resgister.get(6).getLocalDateTime().toLocalDate().isEqual(LocalDate.of(2023,5,21)));
        System.out.println(res);



    }
}