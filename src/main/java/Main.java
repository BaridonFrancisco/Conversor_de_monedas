import models.CurrencyType;
import models.Register;
import service.CurrencyManager;
import utils.io.HandlerProperties;
import utils.io.IoRegister;
import view.Menu;

import java.io.IOException;
import java.math.BigDecimal;

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
        //service.CurrencyManager currencyManager=new service.CurrencyManager();
        // var s=currencyManager.pairConversion("USD","EUR");
        /*var re=currencyManager.typeExchange("ARS");
        re.showConversionRate();*/
      /*  Gson gson=new Gson();
        int[][] ints2 = gson.fromJson("[[1,2],[3,2]]", int[][].class);
        System.out.println(Arrays.deepToString(ints2))*/
        ;
      /*  var quota= currencyManager.quotaRequest();
        System.out.println(quota.requestMax());
        System.out.println(quota.dayResetMonth());
        System.out.println(quota.requestRemaining());*/

        /*LocalDateTime va=LocalDateTime.now();
        System.out.println(va);*/

//        CurrencyManager currencyManager=new CurrencyManager();
//        BigDecimal amount=new BigDecimal("10000");
//        var x=currencyManager.pairConversion("ARS","USD",amount);

       /* IoRegister writeIO=new IoRegister();
        writeIO.readRegister("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\utils\\io\\arcibvo.txt");*/
      /*  HandlerProperties handlerProperties=new HandlerProperties("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\utils\\properties\\configuration.properties");
        handlerProperties.changeAPIKey("value");*/
      /*  CurrencyManager currencyManager=new CurrencyManager();
        currencyManager.monedas();*/
        new Menu().menuConversion();
       /* IoRegister io=new IoRegister();
       var resultado=io.getAllRegisters("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\utils\\io\\arcibvo.txt");
       for (Register r:resultado){
           System.out.println(r);
       }*/


    }
}