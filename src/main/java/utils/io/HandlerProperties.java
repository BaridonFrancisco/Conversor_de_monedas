package utils.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class HandlerProperties {
    private final Properties properties;
    private final String path = "C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\resources\\configuration.properties";

    public HandlerProperties() throws IOException {
        this.properties = new Properties();
        loadProperties();

    }

    /**
     * load the keys and values into the Properties file
     * @throws IOException  when the read or write stream is interrupted or fail
     */
    public void loadProperties() throws IOException {
        properties.load(new FileInputStream(this.path));
    }


    /**
     * retrieve value associate a key in a properties file
     * @param key key
     * @return value bound to key
     */
    public String getValue(String key) {
        if (this.properties.containsKey(key)) {
            return this.properties.getProperty(key);
        }
        return "key not found";

    }

    /**
     * Update api ket into properties file
     * @param value new api key value
     * @throws IOException when the read or write stream is interrupted or fail
     */
    public void changeAPIKey(String value) throws IOException {
        if (properties.containsKey("api_key")) {
            properties.setProperty("api_key", value);
        } else {
            System.out.println("Api key not found");
        }
        this.properties.store(new FileOutputStream(path), "Api key updated");

    }


}
