import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dia2 {
    public static void main(String[] args) throws Exception {
        String apiKey = "k_g23sugoz";
        URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + apiKey);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();

		String[] moviesArray = parseJsonMovies(json);

		List<String> titles = parseTitles(moviesArray);
		titles.forEach(System.out::println);
        List<String> rating = parseRating(moviesArray);
		rating.forEach(System.out::println);
		List<String> urlImages = parseUrlImages(moviesArray);
		urlImages.forEach(System.out::println);
        List<String> years = parseYears(moviesArray);
		years.forEach(System.out::println);

    }
    private static String[] parseJsonMovies(String json) {
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("no match in " + json);
		}

		String[] moviesArray = matcher.group(1).split("\\},\\{");
		moviesArray[0] = moviesArray[0].substring(1);
		int last = moviesArray.length - 1;
		String lastString = moviesArray[last];
		moviesArray[last] = lastString.substring(0, lastString.length() - 1);
		return moviesArray;
	}
	
	private static List<String> parseTitles(String[] moviesArray) {
		return parseAttribute(moviesArray, 3);
	}
	private static List<String> parseYears(String[] moviesArray) {
		return parseAttribute(moviesArray, 4);
	}
	private static List<String> parseUrlImages(String[] moviesArray) {
		return parseAttribute(moviesArray, 5);
	}
    
    private static List<String> parseRating(String[] moviesArray) {
		return parseAttribute(moviesArray, 7);
	}
	private static List<String> parseAttribute(String[] moviesArray, int pos) {
		return Stream.of(moviesArray)
			.map(e -> e.split("\",\"")[pos]) 
			.map(e -> e.split(":\"")[1]) 
			.map(e -> e.replaceAll("\"", ""))
			.collect(Collectors.toList());
	}
}
/* {
    0"id":"tt5491994",
    1"rank":"1",
    2"title":"Planet Earth II",
    3"fullTitle":"Planet Earth II (2016)",
    4"year":"2016",
    5"image":"https://imdb-api.com/images/original/MV5BZWYxODViMGYtMGE2ZC00ZGQ3LThhMWUtYTVkNGE3OWU4NWRkL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMjYwNDA2MDE@._V1_Ratio0.6716_AL_.jpg",
    6"crew":"David Attenborough, Chadden Hunter",
    7"imDbRating":"9.4",
    "imDbRatingCount":"133576"}
 */