import models.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.io.IoRegister;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class IoRegisterTest {
    File file=new File("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\resources\\testWriter.txt");
    private final IoRegister ioRegister=new IoRegister();
    @Test
    @DisplayName("verefica si se genera un archivo de registro de la conversion")
    public void writeFile() throws IOException {
        Currency currency=new Currency(
                "USD",
                new HashMap<>(),
                "ARS",new BigDecimal("0"),
                new BigDecimal("100"));
        if(!file.exists())file.createNewFile();
        Assertions.assertTrue(file.canWrite());
        ioRegister.writeRegister(currency,
                file.getAbsolutePath(),new BigDecimal("91200"));
        if(file.length()>300) file.delete();

    }
    @Test
    @DisplayName("verifica si se puede leer el archivo")
    public void readFile() throws IOException {
        if(file.exists()){
            Assertions.assertTrue(file.canRead());
            ioRegister.readRegister(file.getAbsolutePath());
        }
    }
    @Test
    @DisplayName("verifica que se puedan obtener archivos Register como List")
    public void getAllRegister() throws IOException {
        var allRegister=ioRegister.getAllRegisters(file.getAbsolutePath());
        Assertions.assertTrue(file.canRead());
        Assertions.assertFalse(allRegister.isEmpty());
    }
}
