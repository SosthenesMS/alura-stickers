import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão e buscar o top 250 filmes do imdb
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudoDoIMD extrator = new ExtratorDeConteudoDoIMD();

        // Fazer uma conexão e buscar a foto do dia na API da Nasa
        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        String textoDaMensagem = "TOPZERA";

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeDoArquivo = conteudo.getTitulo() + ".png";

            geradora.criar(inputStream, nomeDoArquivo, textoDaMensagem);

            System.out.println("\u001b[1m Titulo: \u001b[m" + conteudo.getTitulo());
            // System.out.println("\u001b[1m Imagem: \u001b[m" + filme.get("image"));
            // System.out.println("\u001b[45m \u001b[1m Nota IMD: " +
            // filme.get("imDbRating") + " \u001b[m");
            System.out.println();

        }
    }
}
