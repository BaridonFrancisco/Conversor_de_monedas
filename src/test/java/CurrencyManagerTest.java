import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CurrencyManagerTest {
    @Test
    @DisplayName("conversion de tipos gson a objetos Currency")
    public void deserializeCurrency(){
        String jsonString=Util.jsonToString("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\responseExample.json");
        Gson gson=new Gson();
        Currency currency=gson.fromJson(jsonString,Currency.class);
        assertEquals("USD",currency.currencyName());
        assertNotNull(currency.conversionRates());
    }

    public void typeExchange(){
        CurrencyManager currencyManager=new CurrencyManager();


    }
}
