package utils.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class IoJson {

    public static String jsonToString(String path){
        String json="";
        try {
            json=Files.readString(Path.of(path));
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return json;

    }
}
