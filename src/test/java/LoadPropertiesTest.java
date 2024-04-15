import config.HandlerProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LoadPropertiesTest {
    @Test
    public void loadProperties() throws IOException {
        String path="C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\utils\\properties\\configuration.properties";
        HandlerProperties handlerProperties =new HandlerProperties(path);
        handlerProperties.loadProperties();
        var api_key= handlerProperties.getValue("api_key");
        System.out.println(api_key);
        Assertions.assertEquals("ec64479023a3128c0f5f0d37",api_key);


    }
}
