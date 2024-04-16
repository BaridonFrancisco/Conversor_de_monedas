import com.google.gson.Gson;
import config.HandlerProperties;
import models.CodeCurrency;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CurrencyManager {
    HttpClient client = HttpClient.newBuilder()
            .build();
    private final HandlerProperties handlerProperties=new HandlerProperties("C:\\Users\\Owner\\Desktop\\Alura\\Conversor_Monedas\\src\\main\\java\\utils\\properties\\configuration.properties");

    public CurrencyManager() throws IOException {
    }

    public Currency typeExchange(String currency) throws IOException, InterruptedException {
        HttpRequest req = createGetRequest("https://v6.exchangerate-api.com/v6/ec64479023a3128c0f5f0d37/latest/" + currency);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);

    }
    public Currency pairConversion(String currencyBase,String currencyTarget) throws IOException, InterruptedException {
       var uri= String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",handlerProperties.getValue("api_key"),currencyBase,currencyTarget);
       HttpRequest req=createGetRequest(uri);
       String res=getResponse(req,this.client);
       Gson gson =new Gson();
       Currency currency=gson.fromJson(res,Currency.class);
        System.out.println(currency.currencyTarget());
        System.out.println(currency.rateConversion());
        System.out.println(currency.currencyName());
        System.out.println(currency.conversionRates()==null);
        return currency;

    }
    public Currency pairConversion(String currencyBase, String currencyTarget, BigDecimal amount) throws IOException, InterruptedException {
        var uri=String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/"+amount.doubleValue(),handlerProperties.getValue("api_key"),currencyBase,currencyTarget);
        HttpRequest req=createGetRequest(uri);
        String res=getResponse(req,this.client);
        Gson gson =new Gson();
        return gson.fromJson(res,Currency.class);
    }
    public void availableCurrencies() throws IOException, InterruptedException {
        var uri="https://v6.exchangerate-api.com/v6/"+handlerProperties.getValue("api_key")+"/codes";
        HttpRequest req=createGetRequest(uri);
        String res=getResponse(req,this.client);
        Gson gson=new Gson();
        var x=gson.fromJson(res, CodeCurrency.class);
        //String var= Arrays.deepToString(x.arr());
        availableCurrenciesMap(x.arr());
       // System.out.println(var);

    }
    private HttpRequest createGetRequest(String uri) {
        return HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
    }

    private String getResponse(HttpRequest request, HttpClient client) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private Map<String,String> availableCurrenciesMap(String[][] matrix){
        var re=  Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .toList();
        Map<String,String>map=new HashMap<>();
       for(int i=0;i<re.size()-1;i++){
                map.put(re.get(i), re.get(i + 1));
        }
        return map;
    }
}
