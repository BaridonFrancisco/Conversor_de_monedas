import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class CurrencyManagerTest {
    private final String jsonString=Util.jsonToString("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\responseExample.json");
    @Test
    @DisplayName("conversion de tipos gson a objetos Currency")
    public void deserializeCurrency(){
        Gson gson=new Gson();
        Currency currency=gson.fromJson(jsonString,Currency.class);
        assertEquals("USD",currency.currencyName());
        assertNotNull(currency.conversionRates());
    }
    @Test
    @DisplayName("retorna el tipo de cambio de la devisa")
    public void typeExchange() throws IOException, InterruptedException {
        CurrencyManager currencyManager=new CurrencyManager();
        String currencyType="USD";
        Currency currency= currencyManager.typeExchange(currencyType);
        assertNotNull(currency);
        assertEquals("USD",currency.currencyName());
        assertNotNull(currency.conversionRates());
        assertNotNull(currency.conversionRates().get("EUR"));



    }
}
