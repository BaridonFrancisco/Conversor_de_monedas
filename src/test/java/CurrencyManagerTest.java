
import com.google.gson.Gson;
import models.Currency;
import models.Quota;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CurrencyManager;
import utils.io.IoJson;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class CurrencyManagerTest {
    private final CurrencyManager currencyManager = new CurrencyManager();
    private final String jsonString = IoJson.jsonToString("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\resources\\responseExample.json");

    public CurrencyManagerTest() throws IOException {
    }

    @Test
    @DisplayName("conversion de tipos gson a objetos models.Currency")
    public void deserializeCurrency() {
        Gson gson = new Gson();
        Currency currency = gson.fromJson(jsonString, Currency.class);
        assertEquals("USD", currency.currencyName());
        assertNotNull(currency.conversionRates());
    }

    @Test
    @DisplayName("retorna el tipo de cambio de la devisa")
    public void typeExchange() throws IOException, InterruptedException {
        String currencyType = "USD";
        Currency currency = currencyManager.typeExchange(currencyType);
        assertNotNull(currency);
        assertEquals("USD", currency.currencyName());
        assertNotNull(currency.conversionRates());
        assertNotNull(currency.conversionRates().get("EUR"));

    }

    @Test
    @DisplayName("verifica un objeto currency con la equivalencia")
    public void currencyConversion() throws IOException, InterruptedException {
        var currency = currencyManager.pairConversion("EUR", "USD");
        Assertions.assertInstanceOf(BigDecimal.class, currency.rateConversion());
        Assertions.assertEquals("EUR", currency.currencyName());
        Assertions.assertEquals("USD", currency.currencyTarget());
        BigDecimal bigDecimal = new BigDecimal("0.00000");
        assertEquals(1, (currency.rateConversion().compareTo(bigDecimal)));
    }

    @Test
    @DisplayName("verifica si la conversion de las devisas no es nula segun un monto")
    public void currencyConversionAmount() throws IOException, InterruptedException {
        var currency = currencyManager.pairConversion("ARS", "USD", new BigDecimal("100000"));
        Assertions.assertNotNull(currency.conversionResult());
        Assertions.assertEquals("ARS", currency.currencyName());
        Assertions.assertEquals("USD", currency.currencyTarget());

    }

    @Test
    @DisplayName("verifica si los atributos del Objeto son null si ")
    public void currencyReturnTest() throws IOException, InterruptedException {
        var currency = currencyManager.typeExchange("ATTTT");
        System.out.println(currency.currencyName() == null);
        assertNotNull(currency);

    }

    @Test
    @DisplayName("verifica la quota diarias de request")
    public void quotaResquest() throws IOException, InterruptedException {
        Quota quota = currencyManager.quotaRequest();
        Assertions.assertNotNull(quota);

    }

    @Test
    @DisplayName("verifica que el mapa con las divisas disponibles no sea null")
    public void availableCurrenciesTest() throws IOException, InterruptedException {
        var map = currencyManager.availableCurrencies();
        Assertions.assertNotNull(map);
        assertFalse(map.isEmpty());
    }

}
