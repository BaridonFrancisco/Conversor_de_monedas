import utils.io.HandlerProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HandlerPropertiesTest {
    @Test
    @DisplayName("verifica si la api_key de properties se carga correctamente")
    public void getApikey() throws IOException {
        HandlerProperties handlerProperties=new HandlerProperties();
        var api_key= handlerProperties.getValue("api_key");
        System.out.println(api_key);
        Assertions.assertEquals("ec64479023a3128c0f5f0d37",api_key);

    }


}
