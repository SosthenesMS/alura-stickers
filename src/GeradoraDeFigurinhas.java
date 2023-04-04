import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    // ler a imagem
    public void criar(InputStream inputStream, String nomeDoArquivo, String textoDaMensagem) throws Exception {
        // InputStream inputStream = new FileInputStream(new
        // File("assets/shawshank-redemption.jpg"));
        // InputStream inputStream = new URL(
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memorica com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova em memoria
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        
        // graphics.drawImage(imagemOriginal, null, 0, 0);
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        graphics.setColor(Color.YELLOW);
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString(textoDaMensagem, 150, novaAltura - 80);

        // escrever a nova imagem
        ImageIO.write(novaImagem, "png", new File("assets/" + nomeDoArquivo));
    }
}
