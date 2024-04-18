package utils.io;


import models.Currency;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class IoRegister {



    public  void writeRegister(Currency obj, String path, BigDecimal amount){
        try( FileWriter fileWriter=new FileWriter(path,true)) {
            var x= String.format("""
                    ----Register----
                    Date:%s,
                    Currency:%s,
                    Target:%s,
                    result:%s,
                    Amount:%s.
                    """,LocalDateTime.now(),obj.currencyName(),obj.currencyTarget(),obj.conversionResult(),amount);
            fileWriter.write(x);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createMkdir(){
        File file=new File("path");
        if(!file.exists() && file.isFile()){
            file.mkdir();

        }
    }
    public void readRegister(String path) throws IOException {
        try (FileReader fileReader = new FileReader(path)) {
            int i;
            while((i=fileReader.read())!=-1){
                System.out.print((char)i);
            }

        }
    }
}
