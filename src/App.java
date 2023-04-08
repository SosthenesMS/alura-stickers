import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão e buscar o top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extratir só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();

        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados

        for (Map<String, String> filme : listaDeFilmes) {

            String textoDaMensagem = "";
            double notaImdb = Double.parseDouble(filme.get("imDbRating"));
            if (notaImdb > 9) {
                textoDaMensagem = "TOPZERA";
            } else {
                textoDaMensagem = "MEIA BOCA";
            }

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeDoArquivo = titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.criar(inputStream, nomeDoArquivo, textoDaMensagem);

            System.out.println("\u001b[1m Titulo: \u001b[m" + titulo);
            System.out.println("\u001b[1m Imagem: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[45m \u001b[1m Nota IMD: " + filme.get("imDbRating") + " \u001b[m");
            System.out.println();

        }

    }
}
