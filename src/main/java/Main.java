import com.google.gson.Gson;
import models.CurrencyType;
import java.io.IOException;
import java.util.Arrays;

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
        /*var re=currencyManager.typeExchange("ARS");
        re.showConversionRate();*/
        Gson gson=new Gson();
        int[][] ints2 = gson.fromJson("[[1,2],[3,2]]", int[][].class);
        System.out.println(Arrays.deepToString(ints2));
        currencyManager.availableCurrencies();





    }
}
