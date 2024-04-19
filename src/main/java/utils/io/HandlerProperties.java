package utils.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class HandlerProperties {
    private final Properties properties;
    private final String path;

    public HandlerProperties(String path,String api_key_value) throws IOException {
        this.path = path;
        Objects.requireNonNull(path,"Cannot be null");
        Objects.requireNonNull(api_key_value,"Cannot be null");
        this.properties = new Properties();
        loadProperties();
        changeAPIKey(api_key_value);

    }

    public void loadProperties() throws IOException {
        properties.load(new FileInputStream(this.path));
    }
    @SuppressWarnings("")
    public void showProperties(){
            for(Object p:properties.entrySet()){
                System.out.println(p);
            }
    }
    public String getValue(String key){

        if(this.properties.containsKey(key)){
            return this.properties.getProperty(key);
        }
        return "key not found";

    }
    public void changeAPIKey(String value) throws IOException {
        if (properties.containsKey("api_key")) {
            properties.setProperty("api_key", value);
        } else {
            System.out.println("Api key not found");
        }
        this.properties.store(new FileOutputStream(path),"Api key updated");

    }


}
