import models.CurrencyType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Retrieve all environment variables
     /*   Map<String, String> env_var = System.getenv();

        // Loop through all environment variables
        for (String envName : env_var.keySet()) {
            // Print environment variable name and value to console
            System.out.format("%s=%s", envName, env_var.get(envName));
            System.out.println();
        }*/
      var valor = CurrencyType.ALL.name();
        System.out.println(valor);
        CurrencyManager currencyManager=new CurrencyManager();
       // var s=currencyManager.pairConversion("USD","EUR");
        var re=currencyManager.typeExchange("ARS");
        re.showConversionRate();


    }
}
