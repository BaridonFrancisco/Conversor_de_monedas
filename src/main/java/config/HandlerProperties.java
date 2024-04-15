package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HandlerProperties {
    private final Properties properties;
    private String path;

    public HandlerProperties(String path) {
        this.path = path;
        this.properties = new Properties();
    }

    public void loadProperties() throws IOException {
        properties.load(new FileInputStream(this.path));
    }
    public void showProperties(){
            for(Object p:properties.entrySet()){
                System.out.println(p);
            }
    }
    public String getValue(String key){
       return this.properties.getProperty(key);
    }

}
