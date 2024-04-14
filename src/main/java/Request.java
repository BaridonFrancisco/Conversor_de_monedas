import java.net.http.HttpRequest;

public interface Request {
     HttpRequest createResquest(String uri);
}
