import config.HandlerProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HandlerPropertiesTest {
    @Test
    @DisplayName("Retorna la api_key de properties")
    public void getApikey() throws IOException {
        String path="C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\utils\\properties\\configuration.properties";
        HandlerProperties handlerProperties=new HandlerProperties(path);
        var api_key= handlerProperties.getValue("api_key");
        System.out.println(api_key);
        Assertions.assertEquals("ec64479023a3128c0f5f0d37",api_key);


    }


}
