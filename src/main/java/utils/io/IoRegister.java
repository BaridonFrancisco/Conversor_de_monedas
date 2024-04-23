package utils.io;

import models.Currency;
import models.Register;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IoRegister {

    private String path = "C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\historial\\registro.txt";

    /**Write a txt file with the data entered by keyboard
     * @param obj currency to be written
     * @param amount amount to be written
     */
    public void writeRegister(Currency obj, BigDecimal amount) {
        try (FileWriter fileWriter = new FileWriter(this.path, true)) {
            var x = String.format("""
                    ----Register----
                    Date:%s
                    Currency:%s
                    Target:%s
                    result:%s
                    Amount:%s
                    """, LocalDateTime.now(), obj.currencyName(), obj.currencyTarget(), obj.conversionResult(), amount);
            fileWriter.write(x);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *read the content of a text file
     * @param path path txt file
     * @throws IOException when the read or write stream is interrupted or fail
     */
    // lee archivos de txt en el test
    public void readRegister(String path) throws IOException {
        try (FileReader fileReader = new FileReader(path)) {
            if (fileReader.read() == -1) {
                System.out.println("No existen registros");
            }
            int i;
            while ((i = fileReader.read()) != -1) {
                System.out.print((char) i);
            }

        }
    }

    /**
     * retrieve all register into text file and then transforms to dynamic array
     * @return all register as List
     * @throws IOException when the read or write stream is interrupted or fail
     */
    public List<Register> getAllRegisters() throws IOException {
        var re = Files.readString(Path.of(this.path));
        var li = Arrays.stream(re.split("\n"))
                .filter(s -> !s.contains("----Register----"))
                .map(s -> s.substring(s.indexOf(":") + 1))
                .toList();
        return listRegisters(li);

    }

    /**
     * transforms the array contained in the file into a list of registers
     * @param registers as String
     * @return List types of Register
     */
    private List<Register> listRegisters(List<String> registers) {
        LocalDateTime date;
        String currencyBase;
        String targetBase;
        BigDecimal result;
        BigDecimal amount;
        List<Register> listRegister = new ArrayList<>();
        for (int i = 0; i < registers.size(); i += 5) {
            date = LocalDateTime.parse(registers.get(i));
            currencyBase = registers.get(i + 1);
            targetBase = registers.get(i + 2);
            result = new BigDecimal(registers.get(i + 3));
            amount = new BigDecimal(registers.get(i + 4));
            listRegister.add(new Register(date, currencyBase, targetBase, result, amount));
        }
        return listRegister;

    }

    public void setPath(String path) {
        this.path = path;
    }

}
