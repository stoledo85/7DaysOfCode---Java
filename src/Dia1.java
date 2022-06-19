import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

/**
 * Dia 1: Usar essa API para pesquisar os top 250 filmes e imprimir o JSON
 * correspondente.
 */
public class Dia1 {

    public static void main(String[] args) throws Exception {
        String apiKey = "k_g23sugoz";
        URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        System.out.println("Resposta: " + json);
    }
}
