import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyManager {
    HttpClient client= HttpClient.newBuilder()
                .build();

    public void typeExchange(String type) throws IOException, InterruptedException {
        HttpRequest req=createGetRequest("https://v6.exchangerate-api.com/v6/ec64479023a3128c0f5f0d37/latest/"+type);
        String res=getResponse(req,this.client);
        Gson gson=new Gson();
        //var s=gson.fromJson(res,Currency.class);
        System.out.println(res);
        


    }
    private HttpRequest createGetRequest(String uri){
        return HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
    }
    private String getResponse(HttpRequest request,HttpClient client) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
