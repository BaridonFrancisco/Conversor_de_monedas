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

    public Currency typeExchange(String currency) throws IOException, InterruptedException {
        HttpRequest req = createGetRequest("https://v6.exchangerate-api.com/v6/ec64479023a3128c0f5f0d37/latest/" + currency);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);

    }

    public Currency pairConversion(String currencyBase, String currencyTarget) throws IOException, InterruptedException {
        var uri = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", handlerProperties.getValue("api_key"), currencyBase, currencyTarget);
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);


    }

    public Currency pairConversion(String currencyBase, String currencyTarget, BigDecimal amount) throws IOException, InterruptedException {
        var uri = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/" + amount.doubleValue(), handlerProperties.getValue("api_key"), currencyBase, currencyTarget);
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Currency.class);
    }

    public Map<String, String> availableCurrencies() throws IOException, InterruptedException {
        var uri = "https://v6.exchangerate-api.com/v6/" + handlerProperties.getValue("api_key") + "/codes";
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        var x = gson.fromJson(res, CodeCurrency.class);
        return availableCurrenciesMap(x.arr());
    }

    public Quota quotaRequest() throws IOException, InterruptedException {
        var uri = "https://v6.exchangerate-api.com/v6/" + handlerProperties.getValue("api_key") + "/quota";
        HttpRequest req = createGetRequest(uri);
        String res = getResponse(req, this.client);
        Gson gson = new Gson();
        return gson.fromJson(res, Quota.class);
    }

    private HttpRequest createGetRequest(String uri) {
        return HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
    }

    private String getResponse(HttpRequest request, HttpClient client) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private Map<String, String> availableCurrenciesMap(String[][] matrix) {
        var re = Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .toList();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < re.size() - 1; i++) {
            map.put(re.get(i), re.get(i + 1));
        }
        return map;
    }

    public void apiKeyIsNotValid() throws IOException, InterruptedException {
        while (quotaRequest().result().equals("error")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese sun api key");
            String apikey = Objects.requireNonNull(sc.next());
            handlerProperties.changeAPIKey(apikey);
        }


    }

}
