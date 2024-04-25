package service;

import com.google.gson.Gson;
import utils.io.HandlerProperties;
import models.CodeCurrency;
import models.Currency;
import models.Quota;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class CurrencyManager {
    HttpClient client = HttpClient.newBuilder()
            .build();
    private final HandlerProperties handlerProperties;


    public CurrencyManager() throws IOException, InterruptedException {
        this.handlerProperties = new HandlerProperties();
        apiKeyIsNotValid();


    }

    /**shows the exchange rate against all currencies
     * @param currency is identifier currency under the ISO 4217 standard
     * @return a Currency with a result of conversion as BigDecimal
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    public Currency typeExchange(String currency) throws IOException, InterruptedException {
        HttpRequest req = createGetRequest("https://v6.exchangerate-api.com/v6/"+handlerProperties.getValue("api_key")+"/latest/" + currency);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);

    }


    /**
     * performs a one-to-one conversion between currencies with an amount of 1
     * @param currencyBase represents the initial currency of the conversion identif
     * @param currencyTarget represents the target currency of the conversion
     * @return a Currency with a result of conversion as BigDecimal
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    public Currency pairConversion(String currencyBase, String currencyTarget) throws IOException, InterruptedException {
        var uri = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", handlerProperties.getValue("api_key"), currencyBase, currencyTarget);
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);


    }

    /**
     * convert an amount from one currency to another
     * @param currencyBase represents the initial currency of the conversion
     * @param currencyTarget represents the target currency of the conversion
     * @param amount amount to convert
     * @return a Currency with a result of conversion as BigDecimal
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    public Currency pairConversion(String currencyBase, String currencyTarget, BigDecimal amount) throws IOException, InterruptedException {
        var uri = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/" + amount.doubleValue(), handlerProperties.getValue("api_key"), currencyBase, currencyTarget);
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);
    }

    /**
     show all currency available
     * @return  Map String key identifier value  name currency
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    public Map<String,String> availableCurrencies() throws IOException, InterruptedException {
        var uri = "https://v6.exchangerate-api.com/v6/" + handlerProperties.getValue("api_key") + "/codes";
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        var x = gson.fromJson(res, CodeCurrency.class);
        System.out.println(Arrays.deepToString(x.arr()));
        return availableCurrenciesMap(x.arr());
    }

    /**
     provides information about the subscribed plan such as remaining requests etc.
     * @return Quota stores the number of maximum requests , resquests remaining and reset day
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    public Quota quotaRequest() throws IOException, InterruptedException {
        var uri = "https://v6.exchangerate-api.com/v6/" + handlerProperties.getValue("api_key") + "/quota";
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Quota.class);
    }


    /**
     create an instance of HttpRequest
     * @param uri as String
     * @return a HttpRequest instance
     */
    private HttpRequest createGetRequest(String uri) {
        return HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
    }

    /**
     * create a response for an api
     * @param request receives a HttpRequest instance
     * @param client receives a HttpClient instance
     * @return json as String
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    private String getResponse(HttpRequest request, HttpClient client) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }


    /**
     * transform an matriz into a Map
     * @param matrix receives a String[][]
     * @return Map<String,String>
     */
    private Map<String, String> availableCurrenciesMap(String[][] matrix) {
        var re = Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .toList();
        Map<String, String> map = new HashMap<>();
        for (int i = 2; i <= re.size(); i+=2) {
            map.put(re.get(i-2), re.get(i - 1));
        }
        return map;
    }

    /**
     * It takes the input of a key through the keyboard and validates it. If it is not valid, the iteration is repeated.
     * @throws IOException if request fail throws IOException
     * @throws InterruptedException if the operation is interrupted HttpClient attempt canceler
     */
    public void apiKeyIsNotValid() throws IOException, InterruptedException {
        while (quotaRequest().result().equals("error")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese su api key");
            String apikey = Objects.requireNonNull(sc.next());
            handlerProperties.changeAPIKey(apikey);
        }


    }
    public void resetAPIKey() throws IOException, InterruptedException {
        handlerProperties.changeAPIKey("");
        apiKeyIsNotValid();
    }

}
