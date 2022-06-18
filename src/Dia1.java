import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpClient;

/**
 * Dia 1: Usar essa API para pesquisar os top 250 filmes e imprimir o JSON correspondente.
 */
public class Dia1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://imdb-api.com/pt_br/API/Top250Movies/k_g23sugoz"))
                .build();

        HttpClient HTTPclient = HttpClient.newBuilder()
                .build();
        HttpResponse<String> response = HTTPclient.send(request, BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
